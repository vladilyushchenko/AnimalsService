package com.leverx.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Dogs;
import cds.gen.com.sap.animalsservice.entities.Dogs_;
import com.leverx.cap.animalsservice.dto.DogsDto;
import com.leverx.cap.animalsservice.mapper.DogsMapper;
import com.leverx.cap.animalsservice.repository.DogsRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DogsRepositoryImpl implements DogsRepository {

    private final PersistenceService db;
    private final DogsMapper mapper;

    @Override
    public DogsDto create(DogsDto dogsDto) {
        CqnInsert insertToDogs = Insert
                .into(Dogs_.CDS_NAME)
                .entry(mapper.mapDogsDtoToEntity(dogsDto));

        dogsDto.setId(db.run(insertToDogs)
                .single(Dogs.class)
                .getId());
        return dogsDto;
    }

    @Override
    public void update(DogsDto dogsDto) {
        CqnUpdate update = Update
                .entity(Dogs_.CDS_NAME)
                .data(mapper.mapDogsDtoToEntity(dogsDto));
        db.run(update);
    }
}
