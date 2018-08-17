package org.universe.realestatebiddingsystem.user.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseModel {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String telephone;
    private String town;
    private List<RoleResponseModel> rolesList = new ArrayList<>();
    private Boolean isBanned = false;
}
