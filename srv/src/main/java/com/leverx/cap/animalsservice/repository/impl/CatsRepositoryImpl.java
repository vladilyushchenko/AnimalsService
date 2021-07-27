package com.leverx.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Cats;
import cds.gen.com.sap.animalsservice.entities.Cats_;
import com.leverx.cap.animalsservice.dto.CatsDto;
import com.leverx.cap.animalsservice.mapper.CatsMapper;
import com.leverx.cap.animalsservice.repository.CatsRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnUpdate;
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
                .entry(mapper.mapCatsDtoToEntity(catsDto));

        catsDto.setId(db.run(insertToCats)
                .single(Cats.class)
                .getId());
        return catsDto;
    }

    @Override
    public void update(CatsDto catsDto) {
        CqnUpdate update = Update
                .entity(Cats_.CDS_NAME)
                .data(mapper.mapCatsDtoToEntity(catsDto));
        db.run(update);
    }

}
