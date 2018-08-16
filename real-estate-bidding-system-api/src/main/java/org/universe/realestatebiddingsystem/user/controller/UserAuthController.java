package org.universe.realestatebiddingsystem.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.universe.realestatebiddingsystem.user.model.request.LoginRequestModel;
import org.universe.realestatebiddingsystem.user.model.request.RegisterRequestModel;
import org.universe.realestatebiddingsystem.user.service.api.IUserService;

import javax.validation.Valid;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.LOGIN_URL;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.REGISTER_URL;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.USERS_URL;

@RestController
@RequestMapping(USERS_URL)
public class UserAuthController {

    private final IUserService userService;

    @Autowired
    public UserAuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(REGISTER_URL)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestModel requestModel, Errors errors) {
        return this.userService.registerUser(requestModel, errors);
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestModel requestModel, Errors errors) {
        return this.userService.loginUser(requestModel, errors);
    }
}
