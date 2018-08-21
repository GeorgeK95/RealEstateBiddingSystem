package org.universe.realestatebiddingsystem.estates.estate.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;

@Data
public class NewEstateRequestModel {
    @NotBlank
    private String action;

    @NotBlank
    private String type;

    @NotBlank
    private String city;

    @NotNull
    @Min(0)
    private Integer area;

    @NotNull
    private File coverImage;

//    private File image1;
//    private File image2;
//    private File image3;

    private String additionalInfo;

    private PeculiarityViewModel[] peculiarities;
}
