package com.sap.cap.animalsservice.handlers;

import cds.gen.animalsservice.AnimalsService_;
import cds.gen.com.sap.animalsservice.entities.Animals;
import cds.gen.com.sap.animalsservice.entities.Cats;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cap.animalsservice.dto.CatsDto;
import com.sap.cap.animalsservice.dto.DogsDto;
import com.sap.cap.animalsservice.service.CatsService;
import com.sap.cap.animalsservice.service.DogsService;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
@ServiceName(AnimalsService_.CDS_NAME)
public class AnimalsServiceHandler implements EventHandler {

    private final PersistenceService db;
    private final ObjectMapper mapper;
    private final DogsService dogsService;
    private final CatsService catsService;

    @On(event = CdsService.EVENT_CREATE, entity = cds.gen.animalsservice.Cats_.CDS_NAME)
    public void createCats(CdsCreateEventContext context) {
        log.info("createCats() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach((e) -> {
            CatsDto catsDto = mapper.convertValue(e, CatsDto.class);
            catsDto = catsService.createCats(catsDto);
            e.put(Animals.ID, catsDto.getId());
        });
        context.setResult(context.getCqn().entries());
    }

    @On(event = CdsService.EVENT_CREATE, entity = cds.gen.animalsservice.Dogs_.CDS_NAME)
    public void createDogs(CdsCreateEventContext context) {
        log.info("createDogs() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach((e) -> {
            DogsDto dogsDto = mapper.convertValue(e, DogsDto.class);
            dogsDto = dogsService.createDogs(dogsDto);
            e.put(Animals.ID, dogsDto.getId());
        });
        context.setResult(context.getCqn().entries());
    }
}
