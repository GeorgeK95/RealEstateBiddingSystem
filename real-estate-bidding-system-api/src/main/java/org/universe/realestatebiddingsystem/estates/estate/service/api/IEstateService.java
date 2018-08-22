package org.universe.realestatebiddingsystem.estates.estate.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.estates.bid.model.request.BidRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.model.request.NewEstateRequestModel;

import javax.validation.Valid;

public interface IEstateService {
    ResponseEntity<?> createEstate(NewEstateRequestModel requestModel, Errors errors);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> addBid(Long estateId, @Valid BidRequestModel bidRequestModel, Errors errors);
}
