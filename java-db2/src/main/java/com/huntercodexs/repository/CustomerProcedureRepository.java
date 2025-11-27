package com.huntercodexs.repository;

import com.huntercodexs.model.CustomerEntity;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerProcedureRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<CustomerEntity> searchByName(String name) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("DB2INST1.SEARCH_CUSTOMERS_BY_NAME", CustomerEntity.class);

        query.registerStoredProcedureParameter("P_NAME", String.class, ParameterMode.IN);
        query.setParameter("P_NAME", name);

        boolean executed = query.execute();

        if (executed) {
            return query.getResultList();
        }

        return List.of();
    }
}
