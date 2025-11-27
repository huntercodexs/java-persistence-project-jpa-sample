package com.huntercodexs.service;

import com.huntercodexs.model.CustomerEntity;
import com.huntercodexs.repository.CustomerProcedureJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerJdbcService {

    private final CustomerProcedureJdbcRepository repository;

    public List<CustomerEntity> searchByName(String name) {
        return repository.searchByName(name);
    }
}

