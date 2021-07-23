package com.leverx.cap.animalsservice.service;

import com.leverx.cap.animalsservice.dto.DogsDto;

public interface DogsService {

    DogsDto create(DogsDto dogsDto);

    DogsDto update(DogsDto dogsDto);
}
