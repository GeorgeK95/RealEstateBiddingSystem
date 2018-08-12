package org.universe.realestatebiddingsystem.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.universe.realestatebiddingsystem.app.model.response.HttpErrorResponseModel;
import org.universe.realestatebiddingsystem.app.util.StringUtils;
import org.universe.realestatebiddingsystem.user.model.request.RegisterRequestModel;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

public abstract class BaseService<T> {

    protected final UserRepository userRepository;

    protected BaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected ResponseEntity<?> validateSignUpData(RegisterRequestModel userDto) {
        if (userRepository.existsByEmail(userDto.getEmail()))
            return new ResponseEntity(new HttpErrorResponseModel(HttpStatus.CONFLICT.value(), EMAIL_ADDRESS_ALREADY_IN_USE_MESSAGE),
                    HttpStatus.CONFLICT);

        if (!userDto.getPassword().equals(userDto.getConfirm()))
            return new ResponseEntity(new HttpErrorResponseModel(HttpStatus.BAD_REQUEST.value(), PASSWORDS_MISMATCH_MESSAGE),
                    HttpStatus.BAD_REQUEST);

        return null;
    }

    protected Map<String, String> processErrors(Errors errors) {
        Map<String, String> errorsByField = new HashMap<>();
        for (FieldError current : errors.getFieldErrors())
            errorsByField.put(current.getField(),
                    StringUtils.capitalize(current.getField()) + FIELD + current.getDefaultMessage() + DOT
            );
        return errorsByField;
    }
}
