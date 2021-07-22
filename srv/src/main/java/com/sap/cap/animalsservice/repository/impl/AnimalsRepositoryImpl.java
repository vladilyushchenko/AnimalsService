package com.sap.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Animals;
import cds.gen.com.sap.animalsservice.entities.Animals_;
import com.sap.cap.animalsservice.dto.AnimalsDto;
import com.sap.cap.animalsservice.mapper.AnimalsMapper;
import com.sap.cap.animalsservice.repository.AnimalsRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final PersistenceService db;
    private final AnimalsMapper mapper;

    @Override
    public AnimalsDto create(AnimalsDto animalsDto) {
        CqnInsert insert = Insert
                .into(Animals_.CDS_NAME)
                .entry(mapper.mapAnimalsDtoToEntityMap(animalsDto));

        animalsDto.setId(db.run(insert)
                .single(Animals.class)
                .getId());
        return animalsDto;
    }
}
