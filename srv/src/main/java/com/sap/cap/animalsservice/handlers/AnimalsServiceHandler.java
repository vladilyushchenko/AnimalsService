package com.sap.cap.animalsservice.handlers;

import cds.gen.animalsservice.AnimalsService_;
import cds.gen.com.sap.animalsservice.entities.Animals;
import cds.gen.com.sap.animalsservice.entities.Animals_;
import cds.gen.com.sap.animalsservice.entities.Cats;
import cds.gen.com.sap.animalsservice.entities.Dogs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cap.animalsservice.dto.DogsDto;
import com.sap.cap.animalsservice.service.AnimalsService;
import com.sap.cap.animalsservice.service.DogsService;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
@ServiceName(AnimalsService_.CDS_NAME)
public class AnimalsServiceHandler implements EventHandler {

    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final PersistenceService db;
    private final ObjectMapper mapper;
    private final DogsService dogsService;
    // TODO: make comment real
    // private final CatsService catsService;


    @On(event = CdsService.EVENT_CREATE, entity = cds.gen.animalsservice.Cats_.CDS_NAME)
    public void createCats(CdsCreateEventContext context) {
        log.info("createCats() with context : " + context.toString());
        context.getCqn().entries().forEach((e) -> {
            CqnInsert insertToAnimals = Insert
                    .into(Animals_.CDS_NAME)
                    .entry(mapCatsToAnimals(e));

            String id = (String) db.run(insertToAnimals).single().get(Animals.ID);
            e.put(cds.gen.animalsservice.Cats.ID, id);

            CqnInsert insertToCats = Insert
                    .into(cds.gen.com.sap.animalsservice.entities.Cats_.CDS_NAME)
                    .entry(mapServiceCatsToEntity(e));
            db.run(insertToCats).single(Cats.class);
        });
        context.setResult(context.getCqn().entries());
    }

    @On(event = CdsService.EVENT_CREATE, entity = cds.gen.animalsservice.Dogs_.CDS_NAME)
    public void createDogs(CdsCreateEventContext context) {
        log.info("createDogs() with context : " + context.toString());
        context.getCqn().entries().forEach((e) -> {
            DogsDto dogsDto = mapper.convertValue(e, DogsDto.class);
            dogsDto = dogsService.createDogs(dogsDto);
            e.put(Animals.ID, dogsDto.getId());
        });
        context.setResult(context.getCqn().entries());
    }

    private Map<String, Object> catsEntityToMap(Cats cats) {
        Map<String, Object> map = new HashMap<>();
        map.put(Cats.ANIMALS_ID, cats.getAnimalsId());
        map.put(Cats.CATBREED, cats.getCatbreed());
        return map;
    }

    private Map<String, Object> mapServiceCatsToEntity(final Map<String, Object> serviceCats) {
        Map<String, Object> entity = new HashMap<>();
        entity.put(Cats.CATBREED, serviceCats.get(cds.gen.animalsservice.Cats.CATBREED));
        entity.put(Cats.ANIMALS_ID, serviceCats.get(cds.gen.animalsservice.Cats.ID));
        return entity;
    }

    private Map<String, Object> mapCatsToAnimals(final Map<String, Object> cats) {
        Map<String, Object> animals = new HashMap<>();
        animals.put(Animals.ID, cats.get(cds.gen.animalsservice.Cats.ID));
        animals.put(Animals.OWNER_ID, cats.get(cds.gen.animalsservice.Cats.OWNER_ID));
        animals.put(Animals.NAME, cats.get(cds.gen.animalsservice.Cats.NAME));
        animals.put(Animals.TYPE, "CAT");
        return animals;
    }
}
