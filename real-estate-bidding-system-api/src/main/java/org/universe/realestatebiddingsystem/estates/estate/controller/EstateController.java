package org.universe.realestatebiddingsystem.estates.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;
import org.universe.realestatebiddingsystem.estates.city.service.api.ICityService;
import org.universe.realestatebiddingsystem.estates.estate.model.request.EstateRequestModel;
import org.universe.realestatebiddingsystem.estates.type.service.api.ITypeService;

import javax.validation.Valid;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(ESTATES_URL)
@PreAuthorize(IS_AUTHENTICATED)
public class EstateController {

    private final ICityService cityService;
    private final ITypeService typeService;
    private final IEstateService addService;

    @Autowired
    public EstateController(ICityService cityService, ITypeService typeService, IEstateService addService) {
        this.cityService = cityService;
        this.typeService = typeService;
        this.addService = addService;
    }

    @PostMapping(NEW_URL)
    public ResponseEntity<?> getUsers(@Valid @RequestBody EstateRequestModel requestModel, Errors errors) {
        return this.addService.createAdd(requestModel, errors);
    }

    // TODO: refactor in separate controllers
    @GetMapping(CITIES_URL)
    public ResponseEntity<?> getAllCities() {
        return new ResponseEntity<>(this.cityService.getAllCities(), HttpStatus.OK);
    }

    @GetMapping(TYPES_URL)
    public ResponseEntity<?> getAllTypes() {
        return new ResponseEntity<>(this.typeService.getAllTypes(), HttpStatus.OK);
    }
}
