package org.universe.realestatebiddingsystem.user.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.app.model.response.JwtAuthenticationResponseModel;
import org.universe.realestatebiddingsystem.app.security.jwt.JwtTokenProvider;
import org.universe.realestatebiddingsystem.app.service.BaseService;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.app.util.Json;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;
import org.universe.realestatebiddingsystem.user.model.request.LoginRequestModel;
import org.universe.realestatebiddingsystem.user.model.request.RegisterRequestModel;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;
import org.universe.realestatebiddingsystem.user.service.api.IRoleService;
import org.universe.realestatebiddingsystem.user.service.api.IUserService;

import java.util.List;
import java.util.Set;

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
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public ResponseEntity<?> loginUser(LoginRequestModel requestModel, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponseModel(jwt, authentication.getName()));
    }

    @Override
    public ResponseEntity<?> registerUser(RegisterRequestModel requestModel, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        ResponseEntity<?> validated = super.validateSignUpData(requestModel);

        if (validated != null) return validated;

        this.addRoleAndSave(requestModel);

        return new ResponseEntity<>(Json.toJson(USER_REGISTERED_SUCCESSFULLY_MESSAGE, requestModel.getEmail()),
                HttpStatus.CREATED);
    }

    private void addRoleAndSave(RegisterRequestModel requestModel) {
        User user = DTOConverter.convert(requestModel, User.class);
        user.setRoles(Set.of(this.roleService.findByName(RoleName.ROLE_USER).get()));
        super.userRepository.save(user);
    }
}
