package com.example.task2_1_1.controller;

import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    //@PostMapping
    //public ResponseEntity<ResponseApi> create(@RequestBody)
}
