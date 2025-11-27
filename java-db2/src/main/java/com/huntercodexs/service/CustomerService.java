package com.huntercodexs.service;

import com.huntercodexs.model.CustomerEntity;
import com.huntercodexs.repository.CustomerProcedureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerProcedureRepository procedureRepository;

    public List<CustomerEntity> searchByName(String name) {
        return procedureRepository.searchByName(name);
    }
}
