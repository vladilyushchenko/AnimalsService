package com.leverx.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Dogs;
import com.leverx.cap.animalsservice.dto.DogsDto;
import org.mapstruct.Mapper;

@Mapper(uses = AnimalsFactory.class, componentModel = "spring")
public interface DogsMapper {

    Dogs mapDogsDtoToEntity(DogsDto dogsDto);
}
