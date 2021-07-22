package com.sap.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Cats;
import cds.gen.com.sap.animalsservice.entities.Dogs;
import com.sap.cap.animalsservice.dto.CatsDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CatsMapper {

    public Map<String, Object> mapCatsDtoToEntityMap(CatsDto catsDto) {
        Map<String, Object> entity = new HashMap<>();
        entity.put(Cats.CATBREED, catsDto.getCatbreed());
        entity.put(Dogs.ANIMALS_ID, catsDto.getId());
        return entity;
    }
}
