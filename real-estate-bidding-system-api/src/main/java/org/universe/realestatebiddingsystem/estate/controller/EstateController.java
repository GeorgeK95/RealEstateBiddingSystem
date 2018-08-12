package org.universe.realestatebiddingsystem.estate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(ESTATES_URL)
@PreAuthorize(IS_AUTHENTICATED)
public class EstateController {

    @GetMapping(ALL_URL)
    public ResponseEntity<?> registerUser() {
        return new ResponseEntity<>("All estates.", HttpStatus.OK);
    }
}
