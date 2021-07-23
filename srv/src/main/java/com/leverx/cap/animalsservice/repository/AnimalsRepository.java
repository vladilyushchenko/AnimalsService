package com.leverx.cap.animalsservice.repository;

import cds.gen.com.sap.animalsservice.entities.Animals;
import com.leverx.cap.animalsservice.dto.AnimalsDto;

import java.util.List;

public interface AnimalsRepository {

    AnimalsDto create(AnimalsDto animalsDto);

    void updateAnimals(AnimalsDto animalsDto);

    void updateAnimalsList(List<Animals> animals);

    List<Animals> getAnimalsByIds(List<String> ids);

    boolean existsById(String id);
}
