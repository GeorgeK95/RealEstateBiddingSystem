package org.universe.realestatebiddingsystem.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.user.service.api.IUserService;

import javax.servlet.http.HttpServletRequest;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(USERS_URL)
@PreAuthorize(IS_AUTHENTICATED)
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(CURRENT_USER_URL)
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        return this.userService.getUserByToken(request.getHeader(AUTHORIZATION).replace(BEARER_, EMTPY));
    }

    @GetMapping(ID_PATTERN_URL)
    public ResponseEntity<?> findUserById(@PathVariable(ID) Long id) {
        return this.userService.getUserById(id);
    }
}
