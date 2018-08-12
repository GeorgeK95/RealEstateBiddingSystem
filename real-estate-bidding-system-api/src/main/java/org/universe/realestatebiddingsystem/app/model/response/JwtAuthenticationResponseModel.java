package org.universe.realestatebiddingsystem.app.model.response;

import lombok.Data;

@Data
public class JwtAuthenticationResponseModel {
    private String accessToken;
    private String tokenType = "Bearer";
    private String email;

    public JwtAuthenticationResponseModel(String accessToken, String email) {
        this.accessToken = accessToken;
        this.email = email;
    }
}
