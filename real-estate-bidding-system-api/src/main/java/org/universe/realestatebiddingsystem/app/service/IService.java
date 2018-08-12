package org.universe.realestatebiddingsystem.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.user.model.request.RegisterRequestModel;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
}
