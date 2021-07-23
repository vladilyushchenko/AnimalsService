package com.leverx.cap.animalsservice.service.impl;

import cds.gen.animalsservice.Animals_;
import cds.gen.com.sap.animalsservice.entities.Animals;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.leverx.cap.animalsservice.dto.AnimalsDto;
import com.leverx.cap.animalsservice.dto.SwapOwnersDto;
import com.leverx.cap.animalsservice.repository.AnimalsRepository;
import com.leverx.cap.animalsservice.service.AnimalsService;
import com.leverx.cap.animalsservice.service.UsersService;
import com.sap.cds.JSONizable;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class AnimalsServiceImpl implements AnimalsService {

    private final AnimalsRepository animalsRepository;
    private final UsersService usersService;
    private final Gson gson;

    @Override
    public AnimalsDto create(AnimalsDto animalsDto) {
        if (!usersService.existsById(animalsDto.getOwnerId())) {
            throw new ServiceException(ErrorStatuses.NOT_FOUND, gson.toJson(animalsDto));
        }
        animalsDto = animalsRepository.create(animalsDto);
        return animalsDto;
    }

    @Override
    public void swapOwners(SwapOwnersDto swapOwnersDto) {
        List<Animals> animals = animalsRepository.getAnimalsByIds(Stream
                .of(swapOwnersDto.getFirstAnimalsId(), swapOwnersDto.getSecondAnimalsId())
                .collect(toList()));
        if (animals.size() != 2) {
            throw new ServiceException(ErrorStatuses.NOT_FOUND, gson.toJson(swapOwnersDto));
        }
        swapOwnersId(animals.get(0), animals.get(1));
        animalsRepository.updateAnimalsList(animals);
    }

    @Override
    public AnimalsDto update(AnimalsDto animalsDto) {
        if (!usersService.existsById(animalsDto.getOwnerId())) {
            throw new ServiceException(ErrorStatuses.NOT_FOUND, gson.toJson(animalsDto));
        }
        animalsRepository.updateAnimals(animalsDto);
        return animalsDto;
    }

    private void swapOwnersId(Animals animals1, Animals animals2) {
        String tmpId = animals1.getOwnerId();
        animals1.setOwnerId(animals2.getOwnerId());
        animals2.setOwnerId(tmpId);
    }
}
