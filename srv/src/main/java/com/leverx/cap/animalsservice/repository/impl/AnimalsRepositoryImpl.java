package com.leverx.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Animals;
import cds.gen.com.sap.animalsservice.entities.Animals_;
import com.leverx.cap.animalsservice.dto.AnimalsDto;
import com.leverx.cap.animalsservice.mapper.AnimalsMapper;
import com.leverx.cap.animalsservice.repository.AnimalsRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Collections.singletonList;

@Repository
@RequiredArgsConstructor
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final PersistenceService db;
    private final AnimalsMapper mapper;

    @Override
    public AnimalsDto create(AnimalsDto animalsDto) {
        CqnInsert insert = Insert
                .into(Animals_.CDS_NAME)
                .entry(mapper.mapAnimalsDtoToEntity(animalsDto));

        animalsDto.setId(db.run(insert)
                .single(Animals.class)
                .getId());
        return animalsDto;
    }

    @Override
    public void update(AnimalsDto animalsDto) {
        updateList(singletonList(mapper.mapAnimalsDtoToEntity(animalsDto)));
    }

    @Override
    public void updateList(List<Animals> animals) {
        CqnUpdate updateAnimals = Update
                .entity(Animals_.CDS_NAME)
                .entries(animals);
        db.run(updateAnimals);
    }

    @Override
    public List<Animals> getByIds(List<String> ids) {
        CqnSelect selectBothAnimals = Select
                .from(Animals_.class)
                .where(a -> a.ID()
                .in(ids));
        return db.run(selectBothAnimals).listOf(Animals.class);
    }
}
