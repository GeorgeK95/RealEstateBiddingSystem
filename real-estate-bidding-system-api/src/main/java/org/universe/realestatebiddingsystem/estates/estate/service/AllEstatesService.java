package org.universe.realestatebiddingsystem.estates.estate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;
import org.universe.realestatebiddingsystem.estates.estate.model.view.EstateViewModel;
import org.universe.realestatebiddingsystem.estates.estate.repository.EstateRepository;
import org.universe.realestatebiddingsystem.estates.estate.service.api.IAllEstatesService;
import org.universe.realestatebiddingsystem.estates.page.PagedResponseModel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Transactional
@Service
public class AllEstatesService implements IAllEstatesService {

    private final EstateRepository estateRepository;

    @Autowired
    public AllEstatesService(EstateRepository estateRepository) {
        this.estateRepository = estateRepository;
    }

    @Override
    public PagedResponseModel<EstateViewModel> getAllEstatesPaged(int page, int size, Map<String, String[]> parameterMap) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        Page<Estate> allEstates = this.estateRepository.findAll(pageable);
        List<Estate> filteredEstates = allEstates.getContent();

        filteredEstates = this.filterEstates(parameterMap, filteredEstates, allEstates);

        List<EstateViewModel> viewModels = DTOConverter.convert(filteredEstates, EstateViewModel.class);
        return new PagedResponseModel<>(viewModels, allEstates.getNumber(),
                allEstates.getSize(), allEstates.getTotalElements(), allEstates.getTotalPages(), allEstates.isLast());
    }

    private List<Estate> filterEstates(Map<String, String[]> parameterMap, List<Estate> filteredEstates, Page<Estate> allEstates) {
        if (parameterMap.containsKey(CITY) && !parameterMap.get(CITY)[ZERO].equals(UNDEFINED) &&
                parameterMap.get(CITY)[ZERO] != null && !parameterMap.get(AREA)[ZERO].equals(NULL)) {
            filteredEstates = allEstates.stream()
                    .filter(e -> e.getCity().equals(parameterMap.get(CITY)[ZERO]))
                    .collect(Collectors.toList());
            if (parameterMap.containsKey(AREA) && !parameterMap.get(AREA)[ZERO].equals(UNDEFINED) &&
                    !parameterMap.get(AREA)[ZERO].equals("null") && !parameterMap.get(AREA)[ZERO].equals(ZERO_STR)) {
                filteredEstates = allEstates.stream()
                        .filter(e -> e.getCity().equals(parameterMap.get(CITY)[ZERO]) &&
                                e.getArea() <= Double.parseDouble(parameterMap.get(AREA)[ZERO]))
                        .collect(Collectors.toList());
                if (parameterMap.containsKey(PRICE) && !parameterMap.get(PRICE)[ZERO].equals(UNDEFINED) &&
                        !parameterMap.get(PRICE)[ZERO].equals("null") && !parameterMap.get(PRICE)[ZERO].equals(ZERO_STR)) {
                    filteredEstates = allEstates.stream()
                            .filter(e -> e.getCity().equals(parameterMap.get(CITY)[ZERO]) &&
                                    e.getArea() <= Double.parseDouble(parameterMap.get(AREA)[ZERO]) &&
                                    e.getPrice() <= Double.parseDouble(parameterMap.get(PRICE)[ZERO]))
                            .collect(Collectors.toList());
                }
            }
        }

        return filteredEstates;
    }
}
