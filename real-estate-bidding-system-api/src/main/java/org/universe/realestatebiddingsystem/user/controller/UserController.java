package org.universe.realestatebiddingsystem.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.user.model.request.LoginRequestModel;
import org.universe.realestatebiddingsystem.user.model.request.RegisterRequestModel;
import org.universe.realestatebiddingsystem.user.service.api.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

    @PreAuthorize(IS_AUTHENTICATED)
    @GetMapping(CURRENT_USER_URL)
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        return this.userService.getUserByToken(request.getHeader(AUTHORIZATION).replace(BEARER_, EMTPY));
    }

    @PreAuthorize(IS_AUTHENTICATED)
    @GetMapping(ID_PATTERN_URL)
    public ResponseEntity<?> findUserById(@PathVariable(ID) Long id) {
        return this.userService.getUserById(id);
    }
}
