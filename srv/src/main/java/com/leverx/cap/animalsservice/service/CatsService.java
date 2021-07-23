package com.leverx.cap.animalsservice.service;

import com.leverx.cap.animalsservice.dto.CatsDto;

public interface CatsService {

    CatsDto create(CatsDto catsDto);

    CatsDto update(CatsDto catsDto);
}
