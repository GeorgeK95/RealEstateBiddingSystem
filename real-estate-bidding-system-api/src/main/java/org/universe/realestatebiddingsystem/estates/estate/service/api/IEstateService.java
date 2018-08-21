package org.universe.realestatebiddingsystem.estates.estate.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.estates.estate.model.request.NewEstateRequestModel;

public interface IEstateService {
    ResponseEntity<?> createEstate(NewEstateRequestModel requestModel, Errors errors);
}
