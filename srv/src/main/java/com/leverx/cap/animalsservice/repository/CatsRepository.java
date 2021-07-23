package com.leverx.cap.animalsservice.repository;

import com.leverx.cap.animalsservice.dto.CatsDto;

public interface CatsRepository {

    CatsDto create(CatsDto catsDto);

    void update(CatsDto catsDto);
}
