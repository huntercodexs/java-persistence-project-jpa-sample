package com.huntercodexs.controller;

import com.huntercodexs.model.CustomerEntity;
import com.huntercodexs.service.CustomerJdbcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jdbc/customers")
@RequiredArgsConstructor
public class CustomerJdbcController {

    private final CustomerJdbcService service;

    @GetMapping("/search")
    public List<CustomerEntity> search(@RequestParam String name) {
        return service.searchByName(name);
    }
}
