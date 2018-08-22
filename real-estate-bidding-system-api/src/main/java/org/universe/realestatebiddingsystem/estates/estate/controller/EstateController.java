package org.universe.realestatebiddingsystem.estates.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;
import org.universe.realestatebiddingsystem.estates.city.service.api.ICityService;
import org.universe.realestatebiddingsystem.estates.estate.model.request.NewEstateRequestModel;
import org.universe.realestatebiddingsystem.estates.peculiarity.service.api.IPeculiarityService;
import org.universe.realestatebiddingsystem.estates.type.service.api.ITypeService;

import javax.validation.Valid;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(ESTATES_URL)
public class EstateController {

    private final ICityService cityService;
    private final ITypeService typeService;
    private final IEstateService estateService;
    private final IPeculiarityService peculiarityService;

    @Autowired
    public EstateController(ICityService cityService, ITypeService typeService, IEstateService estateService,
                            IPeculiarityService peculiarityService) {
        this.cityService = cityService;
        this.typeService = typeService;
        this.estateService = estateService;
        this.peculiarityService = peculiarityService;
    }

    @PreAuthorize(IS_AUTHENTICATED)
    @PostMapping(NEW_URL)
    public ResponseEntity<?> createEstate(@Valid @RequestBody NewEstateRequestModel requestModel, Errors errors) {
        return this.estateService.createEstate(requestModel, errors);
    }

    @GetMapping(ALL_URL)
    public ResponseEntity<?> getEstates() {
        return this.estateService.findAll();
    }

    @GetMapping(BY_ID_URL)
    public ResponseEntity<?> getEstate(@PathVariable Long id) {
        return this.estateService.findById(id);
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

    @GetMapping(PECULIARITIES_URL)
    public ResponseEntity<?> getAllPeculiarities() {
        return new ResponseEntity<>(this.peculiarityService.getAllPeculiarities(), HttpStatus.OK);
    }
}
