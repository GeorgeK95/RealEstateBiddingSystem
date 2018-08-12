package org.universe.realestatebiddingsystem.user.model.response;

import lombok.Data;

import java.util.List;

@Data
public class UsersResponseModel {
    private List<UserResponseModel> users;
}
