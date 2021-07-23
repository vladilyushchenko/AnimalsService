package com.leverx.cap.animalsservice.service;

import com.leverx.cap.animalsservice.dto.AnimalsDto;
import com.leverx.cap.animalsservice.dto.SwapOwnersDto;

public interface AnimalsService {

    AnimalsDto create(AnimalsDto animalsDto);

    void swapOwners(SwapOwnersDto swapOwnersDto);

    AnimalsDto update(AnimalsDto animalsDto);
}
