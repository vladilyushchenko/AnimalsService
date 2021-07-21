package com.sap.cap.animalsservice.repository;

import com.sap.cap.animalsservice.dto.AnimalsDto;

public interface AnimalsRepository {

    AnimalsDto create(AnimalsDto animalsDto);
}
