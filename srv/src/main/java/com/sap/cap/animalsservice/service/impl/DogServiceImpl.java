package com.sap.cap.animalsservice.service.impl;

import com.sap.cap.animalsservice.dto.DogsDto;
import com.sap.cap.animalsservice.repository.DogsRepository;
import com.sap.cap.animalsservice.service.AnimalsService;
import com.sap.cap.animalsservice.service.DogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogsService {

    private final DogsRepository dogsRepository;
    private final AnimalsService animalsService;

    @Override
    public DogsDto createDogs(DogsDto dogsDto) {
        dogsDto.setId(animalsService.createAnimals(dogsDto).getId());
        dogsRepository.createDogs(dogsDto);
        return dogsDto;
    }
}
