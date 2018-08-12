package org.universe.realestatebiddingsystem.user.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Data
public class LoginRequestModel {

    @NotBlank
    @Size(min = EMAIL_MIN_VALUE, max = EMAIL_MAX_VALUE)
    private String email;

    @NotBlank
    @Size(min = PASSWORD_MIN_VALUE, max = PASSWORD_MAX_VALUE)
    private String password;
}
