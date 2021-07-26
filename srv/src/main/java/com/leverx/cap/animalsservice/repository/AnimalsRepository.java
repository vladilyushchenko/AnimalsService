package com.leverx.cap.animalsservice.repository;

import cds.gen.com.sap.animalsservice.entities.Animals;
import com.leverx.cap.animalsservice.dto.AnimalsDto;

import java.util.List;

public interface AnimalsRepository {

    AnimalsDto create(AnimalsDto animalsDto);

    void update(AnimalsDto animalsDto);

    void updateList(List<Animals> animals);

    List<Animals> getByIds(List<String> ids);
}
