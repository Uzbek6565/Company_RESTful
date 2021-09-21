package com.example.task2_1_1.service;

import com.example.task2_1_1.entity.Company;
import com.example.task2_1_1.entity.Department;
import com.example.task2_1_1.payload.DepartmentDto;
import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.repository.CompanyRepository;
import com.example.task2_1_1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public ResponseApi create(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty())
            return new ResponseApi("Company not found", false);
        Company company = optionalCompany.get();
        if (departmentRepository.existsByNameAndCompany_CorpNameAndCompany_Address(departmentDto.getName(), company.getCorpName(), company.getAddress()))
            return new ResponseApi("The department already exists in the company", false);
        Department department = new Department(null, departmentDto.getName(), company);
        departmentRepository.save(department);
        return new ResponseApi("Department is created", true);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty())
            return null;
        return departmentOptional.get();
    }

    public ResponseApi update(Integer id, DepartmentDto departmentDto) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty())
            return new ResponseApi("Department not found", false);
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty())
            return new ResponseApi("Company not found", false);
        Company company = optionalCompany.get();
        if (departmentRepository.existsByNameAndCompany_CorpNameAndCompany_Address(departmentDto.getName(), company.getCorpName(), company.getAddress()))
            return new ResponseApi("The department already exists in the company", false);
        Department department = departmentOptional.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ResponseApi("Department data is edited", true);
    }

    public ResponseApi deleteById(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty())
            return new ResponseApi("Department not found", false);
        departmentRepository.delete(departmentOptional.get());
        return new ResponseApi("Department is deleted", true);
    }
}
