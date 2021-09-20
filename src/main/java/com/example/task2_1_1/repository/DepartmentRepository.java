package com.example.task2_1_1.repository;

import com.example.task2_1_1.entity.Address;
import com.example.task2_1_1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByNameAndCompany_CorpNameAndCompany_Address(String name, String company_corpName, Address company_address);
}
