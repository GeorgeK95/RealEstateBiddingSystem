package org.universe.realestatebiddingsystem.user.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.model.request.LoginRequestModel;
import org.universe.realestatebiddingsystem.user.model.request.RegisterRequestModel;

public interface IUserService {

    ResponseEntity<?> loginUser(LoginRequestModel requestModel, Errors errors);

    ResponseEntity<?> registerUser(RegisterRequestModel requestModel, Errors errors);

    ResponseEntity<?> getUsers();

    ResponseEntity<?> getUserByToken(String id);
}
