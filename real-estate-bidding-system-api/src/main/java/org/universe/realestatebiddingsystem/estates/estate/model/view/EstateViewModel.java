package org.universe.realestatebiddingsystem.estates.estate.model.view;

import lombok.Data;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;

@Data
public class EstateViewModel {
    private Long id;
    private String action;
    private String type;
    private String city;
    private Integer area;
    private String coverImage;
    private String firstImage;
    private String secondImage;
    private String thirdImage;
    private String additionalInfo;
    private PeculiarityViewModel[] peculiarities;
}
