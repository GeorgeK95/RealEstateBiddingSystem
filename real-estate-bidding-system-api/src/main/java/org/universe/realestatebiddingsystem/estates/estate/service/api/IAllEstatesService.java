package org.universe.realestatebiddingsystem.estates.estate.service.api;

import org.universe.realestatebiddingsystem.estates.estate.model.view.EstateViewModel;
import org.universe.realestatebiddingsystem.estates.page.PagedResponseModel;

import java.util.Map;

public interface IAllEstatesService {
    PagedResponseModel<EstateViewModel> getAllEstatesPaged(int page, int size, Map<String, String[]> parameterMap);
}
