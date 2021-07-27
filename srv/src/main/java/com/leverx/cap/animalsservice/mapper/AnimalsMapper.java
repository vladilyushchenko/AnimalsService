package com.leverx.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Animals;
import com.leverx.cap.animalsservice.dto.AnimalsDto;
import org.mapstruct.Mapper;

@Mapper(uses = AnimalsFactory.class, componentModel = "spring")
public interface AnimalsMapper {

    Animals mapAnimalsDtoToEntity(AnimalsDto animalsDto);
}
