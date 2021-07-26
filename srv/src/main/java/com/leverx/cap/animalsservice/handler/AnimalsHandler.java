package com.leverx.cap.animalsservice.handler;

import cds.gen.animalsservice.AnimalsService_;
import cds.gen.animalsservice.SwapAnimalsOwnersContext;
import com.leverx.cap.animalsservice.dto.SwapOwnersDto;
import com.leverx.cap.animalsservice.service.AnimalsService;
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
public class AnimalsHandler implements EventHandler {

    private final AnimalsService animalsService;

    @On(event = SwapAnimalsOwnersContext.CDS_NAME)
    public void swapAnimalsOwners(SwapAnimalsOwnersContext context) {
        log.info(String.format("swapAnimalsOwners() with ids : %s and %s",
                context.getFirstAnimalId(), context.getSecondAnimalId()));
        animalsService.swapOwners(SwapOwnersDto.builder()
                                .firstAnimalsId(context.getFirstAnimalId())
                                .secondAnimalsId(context.getSecondAnimalId())
                                .build());
        context.setCompleted();
    }
}
