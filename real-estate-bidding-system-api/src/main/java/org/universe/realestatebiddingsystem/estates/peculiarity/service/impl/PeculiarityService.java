package org.universe.realestatebiddingsystem.estates.peculiarity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;
import org.universe.realestatebiddingsystem.estates.peculiarity.repository.PeculiarityRepository;
import org.universe.realestatebiddingsystem.estates.peculiarity.service.api.IPeculiarityService;

@Transactional
@Service
public class PeculiarityService implements IPeculiarityService {

    private final PeculiarityRepository peculiarityRepository;

    @Autowired
    public PeculiarityService(PeculiarityRepository peculiarityRepository) {
        this.peculiarityRepository = peculiarityRepository;
    }

    @Override
    public ResponseEntity<?> getAllPeculiarities() {
        return new ResponseEntity<>(DTOConverter.convert(this.peculiarityRepository.findAll(), PeculiarityViewModel.class),
                HttpStatus.OK);
    }
}
