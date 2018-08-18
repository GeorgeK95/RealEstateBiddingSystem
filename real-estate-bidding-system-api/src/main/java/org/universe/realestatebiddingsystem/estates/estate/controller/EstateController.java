package org.universe.realestatebiddingsystem.estates.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.estates.city.ICityService;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(ESTATES_URL)
@PreAuthorize(IS_AUTHENTICATED)
public class EstateController {

    private final ICityService cityService;

    @Autowired
    public EstateController(ICityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping(ALL_URL)
    public ResponseEntity<?> registerUser() {
        return new ResponseEntity<>("All estates.", HttpStatus.OK);
    }

    @GetMapping(CITIES_URL)
    public ResponseEntity<?> getAllCities() {
        return new ResponseEntity<>(this.cityService.getAllCities(), HttpStatus.OK);
    }

    @GetMapping(TYPES_URL)
    public ResponseEntity<?> getAllTypes() {
        return new ResponseEntity<>(this.cityService.getAllTypes(), HttpStatus.OK);
    }
}
