package com.sap.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Animals;
import cds.gen.com.sap.animalsservice.entities.Animals_;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cap.animalsservice.dto.AnimalsDto;
import com.sap.cap.animalsservice.repository.AnimalsRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final PersistenceService db;
    private final ObjectMapper mapper;

    @Override
    public AnimalsDto create(AnimalsDto animalsDto) {
        CqnInsert insert = Insert
                .into(Animals_.CDS_NAME)
                /* TODO: change this to custom mapper, because jackson
                     will produce map with Dogs/Cats inherited fields */
                .entry(mapper.convertValue(animalsDto, Map.class));

        animalsDto.setId((String) db.run(insert)
                .single()
                .get(Animals.ID)
        );
        return animalsDto;
    }

    private Map<String, Object> mapAnimalsDtoToEntityMap(AnimalsDto animalsDto) {

    }
}
