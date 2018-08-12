package org.universe.realestatebiddingsystem.app.model.response;

import lombok.Data;

@Data
public class JwtAuthenticationResponseModel {
    private String accessToken;
    private String tokenType = "Bearer";
    private String email;
    private String message;

    public JwtAuthenticationResponseModel(String accessToken, String email, String message) {
        this.accessToken = accessToken;
        this.email = email;
        this.message = message;
    }
}
