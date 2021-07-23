package com.leverx.cap.animalsservice.handler;

import cds.gen.animalsservice.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.cap.animalsservice.dto.CatsDto;
import com.leverx.cap.animalsservice.dto.DogsDto;
import com.leverx.cap.animalsservice.dto.SwapOwnersDto;
import com.leverx.cap.animalsservice.service.AnimalsService;
import com.leverx.cap.animalsservice.service.CatsService;
import com.leverx.cap.animalsservice.service.DogsService;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.cds.CdsUpdateEventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ServiceName(AnimalsService_.CDS_NAME)
public class AnimalsServiceHandler implements EventHandler {

    private final ObjectMapper mapper;
    private final DogsService dogsService;
    private final CatsService catsService;
    private final AnimalsService animalsService;

    @On(event = CdsService.EVENT_CREATE, entity = Cats_.CDS_NAME)
    public void createCats(CdsCreateEventContext context) {
        log.info("createCats() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach((e) -> {
            CatsDto catsDto = mapper.convertValue(e, CatsDto.class);
            catsDto = catsService.create(catsDto);
            e.put(Cats.ID, catsDto.getId());
        });
        context.setResult(context.getCqn().entries());
    }

    @On(event = CdsService.EVENT_CREATE, entity = Dogs_.CDS_NAME)
    public void createDogs(CdsCreateEventContext context) {
        log.info("createDogs() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach(e -> {
            DogsDto dogsDto = mapper.convertValue(e, DogsDto.class);
            dogsDto = dogsService.create(dogsDto);
            e.put(Dogs.ID, dogsDto.getId());
        });
        context.setResult(context.getCqn().entries());
    }

    @On(event = CdsService.EVENT_UPDATE, entity = Dogs_.CDS_NAME)
    public void updateDogs(CdsUpdateEventContext context) {
        log.info("updateDogs() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach(e -> {
            DogsDto dogsDto = mapper.convertValue(e, DogsDto.class);
            dogsService.update(dogsDto);
        });
        context.setResult(context.getCqn().entries());
    }

    @On(event = CdsService.EVENT_UPDATE, entity = Cats_.CDS_NAME)
    public void updateCats(CdsUpdateEventContext context) {
        log.info("updateCats() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach(e -> {
            CatsDto catsDto = mapper.convertValue(e, CatsDto.class);
            catsService.update(catsDto);
        });
        context.setResult(context.getCqn().entries());
    }

    @On(event = SwapAnimalsOwnersContext.CDS_NAME)
    public void swapAnimalsOwners(SwapAnimalsOwnersContext context) {
        animalsService.swapOwners(SwapOwnersDto.builder()
                                .firstAnimalsId(context.getFirstAnimalId())
                                .secondAnimalsId(context.getSecondAnimalId())
                                .build());
        context.setCompleted();
    }
}
