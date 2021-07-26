package com.leverx.cap.animalsservice.util;

import cds.gen.animalsservice.Cats;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnDelete;
import com.sap.cds.reflect.CdsModel;
import org.springframework.stereotype.Component;

@Component
public class CqnStatementUtil {

    public static String getIdFromDeleteStatement(CdsModel model, CqnDelete statement) {
        return (String) CqnAnalyzer.create(model)
                .analyze(statement)
                .rootKeys()
                .get(Cats.ID);
    }
}
