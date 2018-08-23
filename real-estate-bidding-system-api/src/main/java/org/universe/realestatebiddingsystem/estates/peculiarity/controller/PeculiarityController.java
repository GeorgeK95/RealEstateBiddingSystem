package org.universe.realestatebiddingsystem.estates.peculiarity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.universe.realestatebiddingsystem.estates.peculiarity.service.api.IPeculiarityService;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.ESTATES_URL;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.PECULIARITIES_URL;

@RestController
@RequestMapping(ESTATES_URL)
public class PeculiarityController {

    private final IPeculiarityService peculiarityService;

    @Autowired
    public PeculiarityController(IPeculiarityService peculiarityService) {
        this.peculiarityService = peculiarityService;
    }

    @GetMapping(PECULIARITIES_URL)
    public ResponseEntity<?> getAllPeculiarities() {
        return new ResponseEntity<>(this.peculiarityService.getAllPeculiarities(), HttpStatus.OK);
    }
}