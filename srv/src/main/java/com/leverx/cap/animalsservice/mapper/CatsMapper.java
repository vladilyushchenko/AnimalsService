package com.leverx.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Cats;
import com.leverx.cap.animalsservice.dto.CatsDto;
import org.springframework.stereotype.Component;

@Component
public class CatsMapper {

    public Cats mapCatsDtoToEntityMap(CatsDto catsDto) {
        Cats cats = Cats.create();
        cats.setCatbreed(catsDto.getCatbreed());
        cats.setId(catsDto.getId());
        return cats;
    }
}
