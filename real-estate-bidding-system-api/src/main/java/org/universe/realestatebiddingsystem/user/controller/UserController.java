package org.universe.realestatebiddingsystem.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.user.model.request.LoginRequestModel;
import org.universe.realestatebiddingsystem.user.model.request.RegisterRequestModel;
import org.universe.realestatebiddingsystem.user.service.api.IUserService;

import javax.validation.Valid;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(USERS_URL)
//@PreAuthorize(IS_AUTHENTICATED)
//@CrossOrigin(origins = "*")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
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
