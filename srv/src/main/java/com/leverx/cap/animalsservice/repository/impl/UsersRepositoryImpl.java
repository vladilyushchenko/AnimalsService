package com.leverx.cap.animalsservice.repository.impl;

import cds.gen.com.sap.animalsservice.entities.Users_;
import com.leverx.cap.animalsservice.repository.UsersRepository;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {

    private final PersistenceService db;

    @Override
    public boolean existsById(String id) {
        CqnSelect select = Select
                .from(Users_.CDS_NAME)
                .byId(id);
        return db.run(select).rowCount() != 0;
    }
}
