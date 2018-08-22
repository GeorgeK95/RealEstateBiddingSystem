package org.universe.realestatebiddingsystem.estates.estate.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.universe.realestatebiddingsystem.app.security.jwt.JwtTokenProvider;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.estates.bid.model.Bid;
import org.universe.realestatebiddingsystem.estates.bid.model.request.BidRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;
import org.universe.realestatebiddingsystem.estates.estate.model.request.NewEstateRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.model.view.EstateViewModel;
import org.universe.realestatebiddingsystem.estates.estate.repository.EstateRepository;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;
import org.universe.realestatebiddingsystem.app.service.BaseService;
import org.universe.realestatebiddingsystem.estates.image.model.entity.Image;
import org.universe.realestatebiddingsystem.estates.image.repository.ImageRepository;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;
import org.universe.realestatebiddingsystem.estates.peculiarity.repository.PeculiarityRepository;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.BID_WITH_PRICE_F_MADE_SUCCESSFULLY_MESSAGE;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.ESTATE_ADDED_SUCCESSFULLY_MESSAGE;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.IMAGE;

@Transactional
@Service
public class EstateService extends BaseService<Estate> implements IEstateService {

    private final EstateRepository estateRepository;
    private final PeculiarityRepository peculiarityRepository;
    private final JwtTokenProvider tokenProvider;
    private final ImageRepository imageRepository;

    @Autowired
    protected EstateService(UserRepository userRepository, EstateRepository estateRepository, PeculiarityRepository peculiarityRepository, JwtTokenProvider tokenProvider, ImageRepository imageRepository) {
        super(userRepository);
        this.estateRepository = estateRepository;
        this.peculiarityRepository = peculiarityRepository;
        this.tokenProvider = tokenProvider;
        this.imageRepository = imageRepository;
    }

    @Override
    public ResponseEntity<?> createEstate(NewEstateRequestModel requestModel, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        this.processAndSaveEstate(requestModel);

        return new ResponseEntity<>(new Gson().toJson(ESTATE_ADDED_SUCCESSFULLY_MESSAGE), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<Estate> allEstates = this.estateRepository.findAll();
        List<EstateViewModel> viewModels = DTOConverter.convert(allEstates, EstateViewModel.class);

        return new ResponseEntity<>(viewModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Estate estateById = this.estateRepository.findById(id).orElseThrow();

        EstateViewModel estateViewModel = DTOConverter.convert(estateById, EstateViewModel.class);

        return new ResponseEntity<>(estateViewModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addBid(Long estateId, @Valid BidRequestModel bidRequestModel, Errors errors) {
        if (errors.hasErrors()) return new ResponseEntity(super.processErrors(errors), HttpStatus.BAD_REQUEST);

        Estate estate = this.estateRepository.findById(estateId).orElseThrow();

        Bid bid = this.processBid(bidRequestModel);
        estate.addBid(bid);

        this.estateRepository.save(estate);

        return new ResponseEntity<>(new Gson().toJson(BID_WITH_PRICE_F_MADE_SUCCESSFULLY_MESSAGE), HttpStatus.CREATED);
    }

    private Bid processBid(BidRequestModel bidRequestModel) {
        Bid bid = DTOConverter.convert(bidRequestModel, Bid.class);
        User author = this.userRepository
                .findById(this.tokenProvider.getUserIdFromJWT(bidRequestModel.getAuthToken()))
                .orElseThrow();
        bid.setAuthor(author);

        return bid;
    }

    private void processAndSaveEstate(NewEstateRequestModel requestModel) {
        Estate toPersist = DTOConverter.convert(requestModel, Estate.class);

        Set<String> names = Arrays.stream(requestModel.getPeculiarities()).map(PeculiarityViewModel::getName)
                .collect(Collectors.toSet());

        Set<Peculiarity> peculiarities = new HashSet<>();
        if (!names.isEmpty()) peculiarities = this.peculiarityRepository.findAllByName(names);

        this.setImages(toPersist, requestModel.getCoverImage(), requestModel.getOrderedImages());

        toPersist.setPeculiarities(peculiarities);

        this.setEstateAuthor(requestModel.getAuthorToken(), toPersist);

        this.estateRepository.save(toPersist);
    }

    private void setImages(Estate toPersist, @NotNull String coverImage, List<String> orderedImages) {
        Image coverImageObject = new Image(IMAGE, coverImage);
        this.imageRepository.save(coverImageObject);

        toPersist.setCoverImage(coverImageObject);
        toPersist.setImages(this.processImages(orderedImages, toPersist));
//        Image img = this.imageService.uploadImage(mpf);
    }

    private void setEstateAuthor(String authorToken, Estate toPersist) {
        Long userId = this.tokenProvider.getUserIdFromJWT(authorToken);
        User author = this.userRepository.findById(userId).orElseThrow();
        toPersist.setAuthor(author);
    }

    private List<Image> processImages(List<String> orderedImagesUrls, Estate toPersist) {
        List<Image> images = new ArrayList<>();

        for (String imageUrl : orderedImagesUrls) {
            Image image = new Image(IMAGE, imageUrl);
            image.setEstate(toPersist);
            this.imageRepository.save(image);
            images.add(new Image(IMAGE, imageUrl));
        }

        return images;
    }
}
