package com.leverx.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Cats;
import com.leverx.cap.animalsservice.dto.CatsDto;
import org.mapstruct.Mapper;

@Mapper(uses = AnimalsFactory.class, componentModel = "spring")
public interface CatsMapper {

    Cats mapCatsDtoToEntity(CatsDto catsDto);
}
