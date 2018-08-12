package org.universe.realestatebiddingsystem.user.model.response;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponseModel {
//    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String telephone;
    private String town;
    private Set<RoleResponseModel> roles = new HashSet<>();
    private Boolean isBanned = false;
}
