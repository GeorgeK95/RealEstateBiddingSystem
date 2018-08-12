package org.universe.realestatebiddingsystem.user.model.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Data
public class RegisterRequestModel {

    @NotBlank
    @Size(min = EMAIL_MIN_VALUE, max = EMAIL_MAX_VALUE)
    @Email
    private String email;

    @NotBlank
    @Size(min = PASSWORD_MIN_VALUE, max = PASSWORD_MAX_VALUE)
    private String password;

    @NotBlank
    @Size(min = PASSWORD_MIN_VALUE, max = PASSWORD_MAX_VALUE)
    private String confirm;

    @NotBlank
    @Size(min = USER_FIRST_NAME_MIN_VALUE, max = USER_FIRST_NAME_MAX_VALUE)
    private String firstName;

    @NotBlank
    @Size(min = USER_LAST_NAME_MIN_VALUE, max = USER_LAST_NAME_MAX_VALUE)
    private String lastName;

    @NotBlank
    @Pattern(regexp = TELEPHONE_REGEXP)
    private String telephone;

//    @NotBlank
    @Size(min = TOWN_MIN_VALUE, max = TOWN_MAX_VALUE)
    private String town;
}