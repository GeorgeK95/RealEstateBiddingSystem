package org.universe.realestatebiddingsystem.user.service.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.app.security.jwt.JwtTokenProvider;
import org.universe.realestatebiddingsystem.app.service.BaseService;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;
import org.universe.realestatebiddingsystem.user.model.request.EditProfileRequestModel;
import org.universe.realestatebiddingsystem.user.repository.RoleRepository;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Service
@Transactional
public class ProfileService extends BaseService<User> implements IProfileService {

    private final JwtTokenProvider tokenProvider;
    private final RoleRepository roleRepository;

    @Autowired
    protected ProfileService(UserRepository userRepository, JwtTokenProvider tokenProvider, RoleRepository roleRepository) {
        super(userRepository);
        this.tokenProvider = tokenProvider;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<?> editUserAccount(EditProfileRequestModel editProfileRequestModel, Errors errors,
                                             Long id, String token) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        User user = this.userRepository.findById(id).orElseThrow();

        //request coming from
        Long submitterId = this.tokenProvider.getUserIdFromJWT(token);
        User submitter = this.userRepository.findById(submitterId).orElseThrow();

        //owner of account or admin only will return true
        boolean matches = new BCryptPasswordEncoder().matches(editProfileRequestModel.getCurrentPassword(), user.getPassword());

        if (submitter.isAdmin() &&
                new BCryptPasswordEncoder().matches(editProfileRequestModel.getCurrentPassword(), submitter.getPassword()))
            matches = true;

        String newPassword = editProfileRequestModel.getNewPassword();
        String confirmNewPassword = editProfileRequestModel.getConfirm();

        if ((!matches || !newPassword.equals(confirmNewPassword)) || (!submitterId.equals(user.getId()) && !submitter.isAdmin()))
            return new ResponseEntity(INVALID_PASSWORD_MESSAGE, HttpStatus.BAD_REQUEST);

        user = this.updateUser(user, DTOConverter.convert(editProfileRequestModel, User.class),
                editProfileRequestModel.getNewPassword());

        this.userRepository.save(user);

        return new ResponseEntity<>(new Gson().toJson(PROFILE_EDITED_SUCCESSFULLY_MESSAGE), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> disableUserAccount(Long id) {
        this.userRepository.banUser(id);

        return new ResponseEntity<>(new Gson().toJson(PROFILE_DISABLED_SUCCESSFULLY_MESSAGE), HttpStatus.OK);
    }

    private User getUser(Long id) {
        Optional<User> userById = this.userRepository.findById(id);

        if (!userById.isPresent()) throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_ID_MESSAGE + id);

        return userById.get();
    }

    private User updateUser(User user, User edited, String newPassword) {
        if (edited.getFirstName() != null && !edited.getFirstName().isEmpty()) user.setFirstName(edited.getFirstName());
        if (edited.getLastName() != null && !edited.getLastName().isEmpty()) user.setLastName(edited.getLastName());
        if (edited.getTelephone() != null && !edited.getTelephone().isEmpty()) user.setTelephone(edited.getTelephone());
        if (edited.getTown() != null && !edited.getTown().isEmpty()) user.setTown(edited.getTown());
        if (newPassword != null && !newPassword.isEmpty())
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));

        if (!edited.getRoles().isEmpty()) {
            user.addRole(this.roleRepository.findByName(RoleName.ROLE_ADMIN).get());
        } else {
            user.setRoles(new HashSet<>());
            user.addRole(this.roleRepository.findByName(RoleName.ROLE_USER).get());
        }

        return user;
    }
}
