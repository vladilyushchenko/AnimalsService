package com.sap.cap.animalsservice.service.impl;

import com.sap.cap.animalsservice.dto.AnimalsDto;
import com.sap.cap.animalsservice.repository.AnimalsRepository;
import com.sap.cap.animalsservice.service.AnimalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalsServiceImpl implements AnimalsService {

    private final AnimalsRepository animalsRepository;

    @Override
    public AnimalsDto createAnimals(AnimalsDto animalsDto) {
        return animalsRepository.create(animalsDto);
    }
}
