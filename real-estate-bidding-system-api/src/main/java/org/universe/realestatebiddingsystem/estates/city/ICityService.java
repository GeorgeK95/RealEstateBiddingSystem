package org.universe.realestatebiddingsystem.estates.city;

import org.springframework.http.ResponseEntity;

public interface ICityService {
    ResponseEntity<?> getAllCities();

    ResponseEntity<?> getAllTypes();
}
