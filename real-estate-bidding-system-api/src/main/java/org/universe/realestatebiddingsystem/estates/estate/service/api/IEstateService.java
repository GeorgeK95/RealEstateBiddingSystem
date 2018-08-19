package org.universe.realestatebiddingsystem.estates.estate.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.estates.estate.model.request.EstateRequestModel;

public interface IEstateService {
    ResponseEntity<?> createAdd(EstateRequestModel requestModel, Errors errors);
}
