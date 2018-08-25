package org.universe.realestatebiddingsystem.estates.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.estates.estate.model.view.EstateViewModel;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;
import org.universe.realestatebiddingsystem.estates.city.service.api.ICityService;
import org.universe.realestatebiddingsystem.estates.estate.model.request.EstateRequestModel;
import org.universe.realestatebiddingsystem.estates.peculiarity.service.api.IPeculiarityService;
import org.universe.realestatebiddingsystem.estates.type.service.api.ITypeService;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> createEstate(@Valid @RequestBody EstateRequestModel requestModel, Errors errors, HttpServletRequest req) {
        String authorToken = req.getHeader(AUTHORIZATION).replace(BEARER_, EMTPY);
        return this.estateService.createEstate(requestModel, errors, authorToken);
    }

    @PreAuthorize(IS_AUTHENTICATED)
    @PutMapping(NEW_URL)
    public ResponseEntity<?> editEstate(@Valid @RequestBody EstateViewModel requestModel, Errors errors, HttpServletRequest req) {
        String authorToken = req.getHeader(AUTHORIZATION).replace(BEARER_, EMTPY);
        return this.estateService.editEstate(requestModel, errors, authorToken);
    }

    @GetMapping(ALL_URL)
    //TODO pagination
    public ResponseEntity<?> getEstates() {
        return this.estateService.findAll();
    }

    @GetMapping(BY_ID_URL)
    public ResponseEntity<?> getEstate(@PathVariable Long id) {
        return this.estateService.findById(id);
    }

    @DeleteMapping(BY_ID_URL)
    public ResponseEntity<?> deleteEstateProcess(@PathVariable Long id) {
        return this.estateService.deleteEstate(id);
    }

}
