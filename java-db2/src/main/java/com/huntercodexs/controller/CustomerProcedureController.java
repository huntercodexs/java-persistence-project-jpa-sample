package com.huntercodexs.controller;

import com.huntercodexs.model.CustomerEntity;
import com.huntercodexs.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedure/customers")
@RequiredArgsConstructor
public class CustomerProcedureController {

    private final CustomerService service;

    @GetMapping("/search")
    public List<CustomerEntity> search(@RequestParam String name) {
        return service.searchByName(name);
    }
}
