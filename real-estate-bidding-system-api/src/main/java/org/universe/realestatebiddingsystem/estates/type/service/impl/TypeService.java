package org.universe.realestatebiddingsystem.estates.type.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.universe.realestatebiddingsystem.app.util.DTOConverter;
import org.universe.realestatebiddingsystem.estates.type.model.response.TypeResponseModel;
import org.universe.realestatebiddingsystem.estates.type.service.api.ITypeService;
import org.universe.realestatebiddingsystem.estates.type.repository.TypeRepository;

@Transactional
@Service
public class TypeService implements ITypeService {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public ResponseEntity<?> getAllTypes() {
        var all = this.typeRepository.findAll();

        return new ResponseEntity<>(DTOConverter.convert(all, TypeResponseModel.class), HttpStatus.OK);
    }
}
