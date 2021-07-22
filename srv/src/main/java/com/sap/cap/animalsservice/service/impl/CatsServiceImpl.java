package com.sap.cap.animalsservice.service.impl;

import com.sap.cap.animalsservice.dto.CatsDto;
import com.sap.cap.animalsservice.repository.CatsRepository;
import com.sap.cap.animalsservice.service.AnimalsService;
import com.sap.cap.animalsservice.service.CatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatsServiceImpl implements CatsService {

    private final CatsRepository catsRepository;
    private final AnimalsService animalsService;

    @Override
    public CatsDto createCats(CatsDto catsDto) {
        catsDto.setId(animalsService.createAnimals(catsDto).getId());
        catsRepository.create(catsDto);
        return catsDto;
    }
}
