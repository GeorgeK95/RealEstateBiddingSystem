package org.universe.realestatebiddingsystem.user.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserProfileResponseModel {
    private String id;
    private String email;
    private String town;
    private String telephone;
    private String firstName;
    private String lastName;
    private String isBanned;
    private List<RoleResponseModel> rolesList = new ArrayList<>();
}
