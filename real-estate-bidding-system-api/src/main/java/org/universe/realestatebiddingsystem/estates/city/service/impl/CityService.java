package org.universe.realestatebiddingsystem.estates.city.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.estates.city.ICityService;
import org.universe.realestatebiddingsystem.estates.estate.mode.response.CityResponseModel;
import org.universe.realestatebiddingsystem.estates.estate.repository.CityRepository;
import org.universe.realestatebiddingsystem.estates.type.service.impl.TypeRepository;

@Transactional
@Service
public class CityService implements ICityService {

    private final CityRepository cityRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public CityService(CityRepository cityRepository, TypeRepository typeRepository) {
        this.cityRepository = cityRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public ResponseEntity<?> getAllCities() {
        var all = this.cityRepository.findAll();

        return new ResponseEntity<>(DTOConverter.convert(all, CityResponseModel.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllTypes() {
        var all = this.typeRepository.findAll();

        return new ResponseEntity<>(DTOConverter.convert(all, CityResponseModel.class), HttpStatus.OK);
    }
}