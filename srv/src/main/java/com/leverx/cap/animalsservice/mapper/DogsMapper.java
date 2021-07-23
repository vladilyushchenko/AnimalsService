package com.leverx.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Dogs;
import com.leverx.cap.animalsservice.dto.DogsDto;
import org.springframework.stereotype.Component;

@Component
public class DogsMapper {

    public Dogs mapDogsDtoToEntity(DogsDto dogsDto) {
        Dogs dogs = Dogs.create();
        dogs.setDogbreed(dogsDto.getDogbreed());
        dogs.setId(dogsDto.getId());
        return dogs;
    }
}
