package org.universe.realestatebiddingsystem.estates.bid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.universe.realestatebiddingsystem.estates.bid.model.request.BidRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;
import org.universe.realestatebiddingsystem.user.model.request.EditProfileRequestModel;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@RestController
@RequestMapping(ESTATES_URL)
@PreAuthorize(IS_AUTHENTICATED)
public class BidController {

    private final IEstateService estateService;

    @Autowired
    public BidController(IEstateService estateService) {
        this.estateService = estateService;
    }

    @PostMapping(BIDS_URL)
    public ResponseEntity<?> createBidProcess(@Valid @RequestBody BidRequestModel bidRequestModel, Errors errors,
                                              @PathVariable Long id, HttpServletRequest req) {
        String authorToken = req.getHeader(AUTHORIZATION).replace(BEARER_, EMTPY);
        return this.estateService.addBid(id, bidRequestModel, errors, authorToken);
    }

}
