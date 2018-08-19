package org.universe.realestatebiddingsystem.estates.estate.model.request;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EstateRequestModel {
    @NotBlank
    private String action;

    @NotBlank
    private String type;

    @NotBlank
    private String city;

    @NotNull
    @Min(1)
    @Max(50)
    private int floor;

    @NotNull
    @Min(0)
    private int area;

    @NotBlank
    private String image;

    @NotBlank
    private String additionalInfo;

    private PeculiarityViewModel[] peculiarities;
}
