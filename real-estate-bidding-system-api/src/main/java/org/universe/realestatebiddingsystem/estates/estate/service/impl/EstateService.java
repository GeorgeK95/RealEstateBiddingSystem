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
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;
import org.universe.realestatebiddingsystem.estates.estate.model.request.NewEstateRequestModel;
import org.universe.realestatebiddingsystem.estates.estate.model.view.EstateViewModel;
import org.universe.realestatebiddingsystem.estates.estate.repository.EstateRepository;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IEstateService;
import org.universe.realestatebiddingsystem.app.service.BaseService;
import org.universe.realestatebiddingsystem.estates.image.model.Image;
import org.universe.realestatebiddingsystem.estates.image.repository.ImageRepository;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.view.PeculiarityViewModel;
import org.universe.realestatebiddingsystem.estates.peculiarity.repository.PeculiarityRepository;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

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
        EstateViewModel estateViewModel = DTOConverter.convert(this.estateRepository.findById(id), EstateViewModel.class);

        return new ResponseEntity<>(estateViewModel, HttpStatus.OK);
    }

    private void processAndSaveEstate(NewEstateRequestModel requestModel) {
        Estate toPersist = DTOConverter.convert(requestModel, Estate.class);

        Set<String> names = Arrays.stream(requestModel.getPeculiarities()).map(PeculiarityViewModel::getName)
                .collect(Collectors.toSet());

        Set<Peculiarity> peculiarities = new HashSet<>();
        if (!names.isEmpty()) peculiarities = this.peculiarityRepository.findAllByName(names);

        toPersist.setCoverImage(new Image(IMAGE, requestModel.getCoverImage()));
        toPersist.setImages(this.processImages(requestModel.getOrderedImages()));
//        Image img = this.imageService.uploadImage(mpf);

        toPersist.setPeculiarities(peculiarities);

        Long userId = this.tokenProvider.getUserIdFromJWT(requestModel.getAuthorToken());
        User author = this.userRepository.findById(userId).orElseThrow();
        toPersist.setAuthor(author);

        this.estateRepository.save(toPersist);
    }

    private List<Image> processImages(List<String> orderedImagesUrls) {
        List<Image> images = new ArrayList<>();

        for (String imageUrl : orderedImagesUrls) {
//            Image image = new Image(IMAGE, imageUrl);
//            this.imageRepository.save(image);
            images.add(new Image(IMAGE, imageUrl));
        }

        return images;
    }
}
