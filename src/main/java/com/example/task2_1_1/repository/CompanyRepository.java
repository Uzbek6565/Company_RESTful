package com.example.task2_1_1.repository;

import com.example.task2_1_1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

}
