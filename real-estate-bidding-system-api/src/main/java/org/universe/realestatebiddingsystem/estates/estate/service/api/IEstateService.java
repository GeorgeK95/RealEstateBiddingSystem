package org.universe.realestatebiddingsystem.estates.estate.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.estates.bid.model.request.BidRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.model.request.EstateRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.model.view.EstateViewModel;

import javax.validation.Valid;

public interface IEstateService {
    ResponseEntity<?> createEstate(EstateRequestModel requestModel, Errors errors,String authorToken);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> addBid(Long estateId, @Valid BidRequestModel bidRequestModel, Errors errors, String authorToken);

    ResponseEntity<?> deleteEstate(Long id);

    ResponseEntity<?> editEstate(EstateViewModel requestModel, Errors errors, String authorToken);
}
