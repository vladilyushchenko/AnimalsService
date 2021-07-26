package com.leverx.cap.animalsservice.service.impl;

import com.leverx.cap.animalsservice.dto.DogsDto;
import com.leverx.cap.animalsservice.repository.DogsRepository;
import com.leverx.cap.animalsservice.service.AnimalsService;
import com.leverx.cap.animalsservice.service.DogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogsServiceImpl implements DogsService {

    private final DogsRepository dogsRepository;
    private final AnimalsService animalsService;

    @Override
    public DogsDto create(DogsDto dogsDto) {
        dogsDto.setId(animalsService.create(dogsDto).getId());
        dogsDto = dogsRepository.create(dogsDto);
        return dogsDto;
    }

    @Override
    public DogsDto update(DogsDto dogsDto) {
        animalsService.update(dogsDto);
        dogsRepository.update(dogsDto);
        return dogsDto;
    }
}
