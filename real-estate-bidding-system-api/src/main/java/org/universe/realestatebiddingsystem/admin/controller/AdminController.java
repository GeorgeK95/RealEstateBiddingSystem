package org.universe.realestatebiddingsystem.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.universe.realestatebiddingsystem.user.service.api.IUserService;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.ADMIN_URL;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.IS_ADMIN;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.USERS_URL;

@RestController
@RequestMapping(ADMIN_URL)
@PreAuthorize(IS_ADMIN)
public class AdminController {

    private final IUserService userService;

    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(USERS_URL)
    public ResponseEntity<?> getUsers() {
        return this.userService.getUsers();
    }
}
