package org.universe.realestatebiddingsystem.estates.estate.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.AREA_MIN_VALUE;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.YOU_MUST_BE_LOGGED_IN_TO_PERFORM_THIS_ACTION_MESSAGE;

@Data
public class NewEstateRequestModel {

    @NotBlank
    private String action;

    @NotBlank
    private String type;

    @NotBlank
    private String city;

    @NotNull
    @Min(AREA_MIN_VALUE)
    private Integer area;

    @NotNull
    private String coverImage;

    private String firstImage;
    private String secondImage;
    private String thirdImage;

    private String additionalInfo;

    @NotBlank(message = YOU_MUST_BE_LOGGED_IN_TO_PERFORM_THIS_ACTION_MESSAGE)
    private String authorToken;

    private PeculiarityViewModel[] peculiarities;

    public List<String> getOrderedImages() {
        List<String> orderedImages = new ArrayList<>();
//        if (this.coverImage != null) orderedImages.add(this.coverImage);
        if (this.firstImage != null) orderedImages.add(this.firstImage);
        if (this.secondImage != null) orderedImages.add(this.secondImage);
        if (this.thirdImage != null) orderedImages.add(this.thirdImage);

        return orderedImages.stream()
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());
    }
}
