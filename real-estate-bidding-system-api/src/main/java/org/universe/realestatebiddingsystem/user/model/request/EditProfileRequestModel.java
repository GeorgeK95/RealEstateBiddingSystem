package org.universe.realestatebiddingsystem.user.model.request;

import lombok.Data;
import org.universe.realestatebiddingsystem.user.model.response.RoleResponseModel;

import javax.validation.constraints.*;

import java.util.Set;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Data
public class EditProfileRequestModel {

    /*@NotBlank
    @Size(min = EMAIL_MIN_VALUE, max = EMAIL_MAX_VALUE)
    @Email
    private String email;*/

    @NotBlank
    @Size(min = PASSWORD_MIN_VALUE, max = PASSWORD_MAX_VALUE, message = INVALID_FIRST_NAME_MESSAGE)
    private String currentPassword;

    //    @NotBlank
//    @Size(min = PASSWORD_MIN_VALUE, max = PASSWORD_MAX_VALUE)
    private String newPassword;

    //    @NotBlank
//    @Size(min = PASSWORD_MIN_VALUE, max = PASSWORD_MAX_VALUE)
    private String confirm;

    //    @NotBlank
//    @Size(min = USER_FIRST_NAME_MIN_VALUE, max = USER_FIRST_NAME_MAX_VALUE)
    @Pattern(regexp = "(^$|.{3,30})", message = INVALID_FIRST_NAME_MESSAGE)
    private String firstName;

    //    @NotBlank
//    @Size(min = USER_LAST_NAME_MIN_VALUE, max = USER_LAST_NAME_MAX_VALUE)
    @Pattern(regexp = "(^$|.{3,30})", message = INVALID_LAST_NAME_MESSAGE)
    private String lastName;

    //    @NotBlank
//    @Size(min = TOWN_MIN_VALUE, max = TOWN_MAX_VALUE, message = INVALID_TOWN_MESSAGE)
    @Pattern(regexp = "(^$|.{3,30})", message = INVALID_TOWN_MESSAGE)
    private String town;

    @Pattern(regexp = TELEPHONE_REGEXP, message = INVALID_PHONE_MESSAGE)
    private String telephone;

    private Set<RoleResponseModel> roles;

    public String getPassword() {
        return null;
    }
}
