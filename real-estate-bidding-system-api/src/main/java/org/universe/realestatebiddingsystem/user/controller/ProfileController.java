package org.universe.realestatebiddingsystem.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.user.model.request.EditProfileRequestModel;
import org.universe.realestatebiddingsystem.user.service.api.IProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(USERS_URL)
@PreAuthorize(IS_ADMIN)
@CrossOrigin(origins = "*")
public class ProfileController {

    private final IProfileService profileService;

    @Autowired
    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping(USERS_DETAILS_ID_URL)
    public ResponseEntity<?> editUserProfile(@Valid @RequestBody EditProfileRequestModel editProfileRequestModel, Errors errors,
                                             @PathVariable Long id, HttpServletRequest req) {
        // req za auth koi sym az
        return this.profileService.editUserProfile(editProfileRequestModel, errors, id);
    }
}
