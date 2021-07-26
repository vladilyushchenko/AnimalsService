package com.leverx.cap.animalsservice.handler;

import cds.gen.animalsservice.AnimalsService_;
import cds.gen.animalsservice.Dogs;
import cds.gen.animalsservice.Dogs_;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.cap.animalsservice.dto.DogsDto;
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
public class DogsHandler implements EventHandler {

    private final ObjectMapper mapper;
    private final DogsService dogsService;

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
}
