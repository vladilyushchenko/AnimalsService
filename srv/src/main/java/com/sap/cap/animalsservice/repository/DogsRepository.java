package com.sap.cap.animalsservice.repository;

import com.sap.cap.animalsservice.dto.DogsDto;

public interface DogsRepository {

    DogsDto createDogs(DogsDto dogsDto);
}
