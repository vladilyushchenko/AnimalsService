package com.sap.cap.animalsservice.handlers;

import cds.gen.animalsservice.AnimalsService_;
import cds.gen.animalsservice.Dogs_;
import cds.gen.animalsservice.SwapAnimalsContext;
import cds.gen.com.sap.animalsservice.entities.Animals;
import cds.gen.com.sap.animalsservice.entities.Animals_;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
@ServiceName(AnimalsService_.CDS_NAME)
public class AnimalsServiceHandler implements EventHandler {

    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final PersistenceService db;

    public AnimalsServiceHandler(PersistenceService db) {
        this.db = db;
    }

    @On(event = SwapAnimalsContext.CDS_NAME)
    public void swapAnimalsAction(SwapAnimalsContext context) {
        log.info("SWAP_CATS_ACTION method called!");
        String firstAnimalId = context.getFirstAnimalId();
        String secondAnimalId = context.getSecondAnimalId();

        CqnSelect selectFirstOwnerId = Select
                .from(Animals_.CDS_NAME)
                .columns(Animals.OWNER_ID)
                .byId(firstAnimalId);
        CqnSelect selectSecondOwnerId = Select
                .from(Animals_.CDS_NAME)
                .columns(Animals.OWNER_ID)
                .byId(secondAnimalId);

        log.info("SQL STATEMENTS PREPARED");
        String firstOwnerId = (String) db.run(selectFirstOwnerId).single().get(Animals.OWNER_ID);
        String secondOwnerId = (String) db.run(selectSecondOwnerId).single().get(Animals.OWNER_ID);

        log.info(String.format("First id : %s, second id : %s", firstOwnerId, secondOwnerId));

        CqnUpdate updateFirstOwner = Update
                .entity(Animals_.class)
                .data(Animals.OWNER_ID, String.valueOf(secondOwnerId))
                .byId(firstAnimalId);
        CqnUpdate updateSecondOwner = Update
                .entity(Animals_.class)
                .data(Animals.OWNER_ID, String.valueOf(firstOwnerId))
                .byId(secondAnimalId);
//                .matching(Collections.singletonMap(Cats.ID, secondCatId));

        log.info("SQL STATEMENTS RUN 1");
        db.run(updateFirstOwner);
        db.run(updateSecondOwner);
        context.setCompleted();
        log.info("SWAP_CATS_ACTION method finished!");
    }

    @On(event = CdsService.EVENT_CREATE, entity = Dogs_.CDS_NAME)
    public void createDogs (CdsCreateEventContext context) {
        List<Map<String, Object>> list = new ArrayList<>(context.getCqn().entries());

        int stop = 0;
    }
}
