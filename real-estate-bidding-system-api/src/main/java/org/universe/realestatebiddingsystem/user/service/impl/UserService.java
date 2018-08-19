package org.universe.realestatebiddingsystem.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.app.model.response.JwtAuthenticationResponseModel;
import org.universe.realestatebiddingsystem.app.security.jwt.JwtTokenProvider;
import org.universe.realestatebiddingsystem.app.service.BaseService;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;
import org.universe.realestatebiddingsystem.user.model.request.LoginRequestModel;
import org.universe.realestatebiddingsystem.user.model.request.RegisterRequestModel;
import org.universe.realestatebiddingsystem.user.model.response.RegisterResponseModel;
import org.universe.realestatebiddingsystem.user.model.response.UserProfileResponseModel;
import org.universe.realestatebiddingsystem.user.model.response.UserResponseModel;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;
import org.universe.realestatebiddingsystem.user.service.api.IRoleService;
import org.universe.realestatebiddingsystem.user.service.api.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.USER_LOGGED_SUCCESSFULLY_MESSAGE;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.USER_NOT_FOUND_WITH_ID_MESSAGE;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.USER_REGISTERED_SUCCESSFULLY_MESSAGE;

@Service
@Transactional
public class UserService extends BaseService<User> implements IUserService {

    private final UserRepository userRepository;

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final IRoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, IRoleService roleService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<?> loginUser(LoginRequestModel requestModel, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.tokenProvider.generateToken(authentication);

        User userByEmail = this.userRepository.findByEmail(requestModel.getEmail());
        String finalMsg = String.format(USER_LOGGED_SUCCESSFULLY_MESSAGE, userByEmail.getFirstName());

        return ResponseEntity.ok(
                new JwtAuthenticationResponseModel(jwt, authentication.getName(), finalMsg, userByEmail.getRoles().size() > 1)
        );
    }

    @Override
    public ResponseEntity<?> registerUser(RegisterRequestModel requestModel, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        ResponseEntity<?> validated = super.validateSignUpData(requestModel);

        if (validated != null) return validated;

        this.addRoleAndSave(requestModel);

        String finalMsg = String.format(USER_REGISTERED_SUCCESSFULLY_MESSAGE, requestModel.getFirstName());
        return new ResponseEntity<>(new RegisterResponseModel(finalMsg, HttpStatus.OK.value()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getUsers() {
        List<User> users = DTOConverter.convert(this.userRepository.findAll(), User.class);

        List<UserResponseModel> usersResponse = DTOConverter.convert(users, UserResponseModel.class);

        UserResponseModel[] userResponseArray = usersResponse.toArray(new UserResponseModel[0]);

        return new ResponseEntity<>(userResponseArray, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserByToken(String id) {
        Long userId = this.tokenProvider.getUserIdFromJWT(id);

        User user = this.getUserByIdOrThrowException(userId);

        return new ResponseEntity<>(DTOConverter.convert(user, UserResponseModel.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        User user = this.getUserByIdOrThrowException(id);

        return new ResponseEntity<>(DTOConverter.convert(user, UserProfileResponseModel.class), HttpStatus.OK);
    }

    private User getUserByIdOrThrowException(Long userId) {
        Optional<User> user = this.userRepository.findById(userId);

        if (!user.isPresent()) throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_ID_MESSAGE + userId);

        return user.get();
    }

    private void addRoleAndSave(RegisterRequestModel requestModel) {
        User user = DTOConverter.convert(requestModel, User.class);
        user.setRoles(Set.of(this.roleService.findByName(RoleName.ROLE_USER).get()));
        super.userRepository.save(user);
    }
}
