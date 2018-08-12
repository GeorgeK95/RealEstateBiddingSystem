package org.universe.realestatebiddingsystem.user.model.response;

import lombok.Data;

@Data
public class RegisterResponseModel {
    private String message;
    private int status;

    public RegisterResponseModel(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
