package org.universe.realestatebiddingsystem.estates.estate.model.view;

import lombok.Data;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;
import org.universe.realestatebiddingsystem.user.model.view.UserViewModel;

import java.util.List;

@Data
public class EstateViewModel {
    private Long id;
    private String action;
    private String type;
    private String additionalInfo;
    private String city;
    private Double area;
    private Double price;
    private ImageViewModel coverImage;
    private List<ImageViewModel> images;
    //    private String firstImage;
//    private String secondImage;
//    private String thirdImage;
    private UserViewModel author;
    private Double lastBid;
    private PeculiarityViewModel[] peculiarities;
}
