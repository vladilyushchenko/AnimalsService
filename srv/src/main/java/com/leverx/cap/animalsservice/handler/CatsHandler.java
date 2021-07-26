package com.leverx.cap.animalsservice.handler;

import cds.gen.animalsservice.AnimalsService_;
import cds.gen.animalsservice.Cats;
import cds.gen.animalsservice.Cats_;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leverx.cap.animalsservice.dto.CatsDto;
import com.leverx.cap.animalsservice.service.CatsService;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsUpdateEventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.sap.cds.services.cds.CdsService.EVENT_CREATE;
import static com.sap.cds.services.cds.CdsService.EVENT_UPDATE;

@Slf4j
@Component
@RequiredArgsConstructor
@ServiceName(AnimalsService_.CDS_NAME)
public class CatsHandler implements EventHandler {

    private final ObjectMapper mapper;
    private final CatsService catsService;


    @On(event = EVENT_CREATE, entity = Cats_.CDS_NAME)
    public void createCats(CdsCreateEventContext context) {
        log.info("createCats() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach((e) -> {
            CatsDto catsDto = mapper.convertValue(e, CatsDto.class);
            catsDto = catsService.create(catsDto);
            e.put(Cats.ID, catsDto.getId());
        });
        context.setResult(context.getCqn().entries());
    }

    @On(event = EVENT_UPDATE, entity = Cats_.CDS_NAME)
    public void updateCats(CdsUpdateEventContext context) {
        log.info("updateCats() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach(e -> {
            CatsDto catsDto = mapper.convertValue(e, CatsDto.class);
            catsService.update(catsDto);
        });
        context.setResult(context.getCqn().entries());
    }
}
