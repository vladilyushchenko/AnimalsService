package com.sap.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Dogs;
import cds.gen.com.sap.animalsservice.entities.Dogs_;
import com.sap.cap.animalsservice.dto.AnimalsDto;
import com.sap.cap.animalsservice.dto.DogsDto;
import com.sap.cap.animalsservice.repository.DogsRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DogsRepositoryImpl implements DogsRepository {

    private final PersistenceService db;

    @Override
    public DogsDto createDogs(DogsDto dogsDto) {
        CqnInsert insertToDogs = Insert
                .into(Dogs_.CDS_NAME)
                .entry(mapDogsDtoToEntityMap(dogsDto));

        dogsDto.setId(db.run(insertToDogs).single(Dogs.class).getAnimalsId());
        return dogsDto;
    }

    private Map<String, Object> mapDogsDtoToEntityMap(DogsDto dogsDto) {
        Map<String, Object> entity = new HashMap<>();
        entity.put(Dogs.DOGBREED, dogsDto.getDogbreed());
        entity.put(Dogs.ANIMALS_ID, dogsDto.getId());
        return entity;
    }
}
