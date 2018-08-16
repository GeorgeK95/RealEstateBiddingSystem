package org.universe.realestatebiddingsystem.user.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
