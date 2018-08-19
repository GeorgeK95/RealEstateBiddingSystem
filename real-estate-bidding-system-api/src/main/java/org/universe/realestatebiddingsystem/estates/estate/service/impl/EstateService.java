package org.universe.realestatebiddingsystem.estates.estate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;
import org.universe.realestatebiddingsystem.estates.estate.model.request.EstateRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;
import org.universe.realestatebiddingsystem.app.service.BaseService;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

@Transactional
@Service
public class EstateService extends BaseService<Estate> implements IEstateService {

    @Autowired
    protected EstateService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public ResponseEntity<?> createAdd(EstateRequestModel requestModel, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);
        String finalMsg = "msasd";
        return new ResponseEntity<>(finalMsg, HttpStatus.CREATED);
    }
}
