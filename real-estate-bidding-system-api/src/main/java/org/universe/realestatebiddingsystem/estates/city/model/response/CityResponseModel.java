package org.universe.realestatebiddingsystem.estates.city.model.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CityResponseModel {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
}
