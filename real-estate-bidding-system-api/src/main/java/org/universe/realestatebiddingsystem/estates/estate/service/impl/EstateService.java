package org.universe.realestatebiddingsystem.estates.estate.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;
import org.universe.realestatebiddingsystem.estates.estate.model.request.NewEstateRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.repository.EstateRepository;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;
import org.universe.realestatebiddingsystem.app.service.BaseService;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;
import org.universe.realestatebiddingsystem.estates.peculiarity.repository.PeculiarityRepository;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.ESTATE_ADDED_SUCCESSFULLY_MESSAGE;

@Transactional
@Service
public class EstateService extends BaseService<Estate> implements IEstateService {

    private final EstateRepository estateRepository;
    private final PeculiarityRepository peculiarityRepository;

    @Autowired
    protected EstateService(UserRepository userRepository, EstateRepository estateRepository, PeculiarityRepository peculiarityRepository) {
        super(userRepository);
        this.estateRepository = estateRepository;
        this.peculiarityRepository = peculiarityRepository;
    }

    @Override
    public ResponseEntity<?> createEstate(NewEstateRequestModel requestModel, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        this.processAndSaveEstate(requestModel);

        return new ResponseEntity<>(new Gson().toJson(ESTATE_ADDED_SUCCESSFULLY_MESSAGE), HttpStatus.CREATED);
    }

    private void processAndSaveEstate(NewEstateRequestModel requestModel) {
        Estate toPersist = DTOConverter.convert(requestModel, Estate.class);

        Set<Peculiarity> peculiarities = this.peculiarityRepository.findAllByName(
                Arrays.stream(requestModel.getPeculiarities())
                        .map(PeculiarityViewModel::getName)
                        .collect(Collectors.toSet())
        );

        toPersist.setPeculiarities(new HashSet<>());
        peculiarities.forEach(toPersist::addPeculiarity);

        this.estateRepository.save(toPersist);
    }
}
