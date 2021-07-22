package com.sap.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Dogs;
import com.sap.cap.animalsservice.dto.DogsDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DogsMapper {

    public Map<String, Object> mapDogsDtoToEntityMap(DogsDto dogsDto) {
        Map<String, Object> entity = new HashMap<>();
        entity.put(Dogs.DOGBREED, dogsDto.getDogbreed());
        entity.put(Dogs.ANIMALS_ID, dogsDto.getId());
        return entity;
    }
}
