package org.universe.realestatebiddingsystem.estates.estate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.estates.estate.mode.response.CityResponseModel;
import org.universe.realestatebiddingsystem.estates.estate.repository.CityRepository;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;

@Transactional
@Service
public class EstateService implements IEstateService {
    private final CityRepository cityRepository;

    @Autowired
    public EstateService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
}
