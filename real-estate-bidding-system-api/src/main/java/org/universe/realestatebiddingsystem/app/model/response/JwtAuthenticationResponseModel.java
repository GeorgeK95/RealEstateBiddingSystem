package org.universe.realestatebiddingsystem.app.model.response;

import lombok.Data;
import org.universe.realestatebiddingsystem.user.model.response.RoleResponseModel;

import java.util.ArrayList;
import java.util.List;

@Data
public class JwtAuthenticationResponseModel {
    private String accessToken;
    private String tokenType = "Bearer";
    private String email;
    private String message;
    private boolean isAdmin;

    public JwtAuthenticationResponseModel(String accessToken, String email, String message, boolean isAdmin) {
        this.accessToken = accessToken;
        this.email = email;
        this.message = message;
        this.isAdmin=isAdmin;
    }
}
