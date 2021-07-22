package com.sap.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Cats;
import cds.gen.com.sap.animalsservice.entities.Cats_;
import com.sap.cap.animalsservice.dto.CatsDto;
import com.sap.cap.animalsservice.mapper.CatsMapper;
import com.sap.cap.animalsservice.repository.CatsRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CatsRepositoryImpl implements CatsRepository {

    private final PersistenceService db;
    private final CatsMapper mapper;

    @Override
    public CatsDto create(CatsDto catsDto) {
        CqnInsert insertToCats = Insert
                .into(Cats_.CDS_NAME)
                .entry(mapper.mapCatsDtoToEntityMap(catsDto));

        catsDto.setId(db.run(insertToCats)
                .single(Cats.class)
                .getAnimalsId());
        return catsDto;
    }
}
