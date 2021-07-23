package com.leverx.cap.animalsservice.service.impl;

import com.leverx.cap.animalsservice.dto.CatsDto;
import com.leverx.cap.animalsservice.repository.CatsRepository;
import com.leverx.cap.animalsservice.service.AnimalsService;
import com.leverx.cap.animalsservice.service.CatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatsServiceImpl implements CatsService {

    private final CatsRepository catsRepository;
    private final AnimalsService animalsService;

    @Override
    public CatsDto create(CatsDto catsDto) {
        catsDto.setId(animalsService.create(catsDto).getId());
        catsDto = catsRepository.create(catsDto);
        return catsDto;
    }

    @Override
    public CatsDto update(CatsDto catsDto) {
        animalsService.update(catsDto);
        catsRepository.update(catsDto);
        return catsDto;
    }
}
