package com.example.task2_1_1.service;

import com.example.task2_1_1.payload.CompanyDto;
import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.repository.AddressRepository;
import com.example.task2_1_1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public ResponseApi create(CompanyDto companyDto) {

    }
}
