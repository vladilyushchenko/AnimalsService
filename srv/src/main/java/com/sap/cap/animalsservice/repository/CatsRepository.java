package com.sap.cap.animalsservice.repository;

import com.sap.cap.animalsservice.dto.CatsDto;

public interface CatsRepository {

    CatsDto create(CatsDto catsDto);
}
