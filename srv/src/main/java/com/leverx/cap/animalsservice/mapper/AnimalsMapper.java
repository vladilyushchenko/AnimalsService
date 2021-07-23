package com.leverx.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Animals;
import com.leverx.cap.animalsservice.dto.AnimalsDto;
import org.springframework.stereotype.Component;

@Component
public class AnimalsMapper {

    // TODO: test on all requests
    public Animals mapAnimalsDtoToEntity(AnimalsDto animalsDto) {
        Animals animals = Animals.create();
        animals.setId(animalsDto.getId());
        animals.setOwnerId(animalsDto.getOwnerId());
        animals.setName(animalsDto.getName());
        animals.setType(animalsDto.getType().name());
        return animals;
    }
}
