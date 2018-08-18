package org.universe.realestatebiddingsystem.user.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.user.model.request.EditProfileRequestModel;

public interface IProfileService {

    ResponseEntity<?> editUserAccount(EditProfileRequestModel editProfileRequestModel, Errors errors, Long id);

    ResponseEntity<?> disableUserAccount(Long id);
}
