package org.universe.realestatebiddingsystem.user.service.api;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.app.service.BaseService;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.model.request.EditProfileRequestModel;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Service
@Transactional
public class ProfileService extends BaseService<User> implements IProfileService {

    protected ProfileService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public ResponseEntity<?> editUserProfile(EditProfileRequestModel editProfileRequestModel, Errors errors, Long id) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        User user = this.userRepository.findById(id).orElseThrow();

        boolean matches = new BCryptPasswordEncoder().matches(editProfileRequestModel.getCurrentPassword(), user.getPassword());
//        String currentPasswordEncrypted = new BCryptPasswordEncoder().encode(editProfileRequestModel.getCurrentPassword());
//        String dbPassword = user.getPassword();
        String newPassword = editProfileRequestModel.getNewPassword();
        String confirmNewPassword = editProfileRequestModel.getConfirm();

        if (!matches || !newPassword.equals(confirmNewPassword))
            return new ResponseEntity(INVALID_CREDENTIALS_MESSAGE, HttpStatus.BAD_REQUEST);

        user = this.updateUser(user, DTOConverter.convert(editProfileRequestModel, User.class),
                editProfileRequestModel.getNewPassword());

        this.userRepository.save(user);

        return new ResponseEntity<>(new Gson().toJson(PROFILE_EDITED_SUCCESSFULLY_MESSAGE), HttpStatus.OK);
    }

    private User updateUser(User user, User edited, String newPassword) {
        if (edited.getFirstName() != null)
            user.setFirstName(edited.getFirstName());
        if (edited.getLastName() != null)
            user.setLastName(edited.getLastName());
        if (edited.getTelephone() != null)
            user.setTelephone(edited.getTelephone());
        if (edited.getTown() != null)
            user.setTown(edited.getTown());
        if (newPassword != null) {
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        }

        return user;
    }
}
