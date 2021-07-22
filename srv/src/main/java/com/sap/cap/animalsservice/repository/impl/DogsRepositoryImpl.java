package com.sap.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Dogs;
import cds.gen.com.sap.animalsservice.entities.Dogs_;
import com.sap.cap.animalsservice.dto.DogsDto;
import com.sap.cap.animalsservice.mapper.DogsMapper;
import com.sap.cap.animalsservice.repository.DogsRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DogsRepositoryImpl implements DogsRepository {

    private final PersistenceService db;
    private final DogsMapper mapper;

    @Override
    public DogsDto createDogs(DogsDto dogsDto) {
        CqnInsert insertToDogs = Insert
                .into(Dogs_.CDS_NAME)
                .entry(mapper.mapDogsDtoToEntityMap(dogsDto));

        dogsDto.setId(db.run(insertToDogs).single(Dogs.class).getAnimalsId());
        return dogsDto;
    }
}
