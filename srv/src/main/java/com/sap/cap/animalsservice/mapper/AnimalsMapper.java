package com.sap.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Animals;
import com.sap.cap.animalsservice.dto.AnimalsDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AnimalsMapper {

    public Map<String, Object> mapAnimalsDtoToEntityMap(AnimalsDto animalsDto) {
        Map<String, Object> entityMap = new HashMap<>();
        entityMap.put(Animals.NAME, animalsDto.getName());
        entityMap.put(Animals.TYPE, animalsDto.getType());
        entityMap.put(Animals.OWNER_ID, animalsDto.getOwnerId());
        return entityMap;
    }
}
