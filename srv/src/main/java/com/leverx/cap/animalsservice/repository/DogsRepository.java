package com.leverx.cap.animalsservice.repository;

import com.leverx.cap.animalsservice.dto.DogsDto;

public interface DogsRepository {

    DogsDto create(DogsDto dogsDto);

    void update(DogsDto dogsDto);
}
