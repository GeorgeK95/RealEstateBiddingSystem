package org.universe.realestatebiddingsystem.estates.type.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.universe.realestatebiddingsystem.estates.type.service.api.ITypeService;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.ESTATES_URL;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.TYPES_URL;

@RestController
@RequestMapping(ESTATES_URL)
public class TypeController {

    private final ITypeService typeService;

    @Autowired
    public TypeController(ITypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping(TYPES_URL)
    public ResponseEntity<?> getAllTypes() {
        return new ResponseEntity<>(this.typeService.getAllTypes(), HttpStatus.OK);
    }
}