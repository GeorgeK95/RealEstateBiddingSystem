package org.universe.realestatebiddingsystem.estates.city.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.estates.city.service.api.ICityService;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(ESTATES_URL)
public class CityController {

    private final ICityService cityService;

    @Autowired
    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(CITIES_URL)
    public ResponseEntity<?> getAllCities() {
        return new ResponseEntity<>(this.cityService.getAllCities(), HttpStatus.OK);
    }
}