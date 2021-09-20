package com.example.task2_1_1.service;

import com.example.task2_1_1.entity.Address;
import com.example.task2_1_1.entity.Company;
import com.example.task2_1_1.payload.CompanyDto;
import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.repository.AddressRepository;
import com.example.task2_1_1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    /**
     * Creating company
     * @param companyDto
     * @return ResponseApi - response message and status of creating result
     */
    public ResponseApi create(CompanyDto companyDto) {
        Optional<Address> address = addressRepository.findById(companyDto.getAddressId());
        if (address.isEmpty())
            return new ResponseApi("Address not found", false);
        if(companyRepository.existsByCorpNameAndAddressAndDirectorName(companyDto.getCorpName(), address.get(), companyDto.getDirectorName()))
            return new ResponseApi("The corporation already exists at this address", false);
        Company company = new Company(null, companyDto.getCorpName(), companyDto.getDirectorName(), address.get());
        companyRepository.save(company);
        return new ResponseApi("Company is created", true);
    }

    /**
     * Getting all companies
     * @return List<Company>
     */
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    /**
     * Getting company by id
     * @param id
     * @return Company
     */
    public Company getById(Integer id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent())
            return company.get();
        return null;
    }

    /**
     * Updatind company data
     * @param id
     * @param companyDto
     * @return ResponseApi - response message and status of updating result
     */
    public ResponseApi update(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty())
            return new ResponseApi("Company not found", false);
        Optional<Address> address = addressRepository.findById(companyDto.getAddressId());
        if (address.isEmpty())
            return new ResponseApi("Address not found", false);
        if(companyRepository.existsByCorpNameAndAddressAndDirectorName(companyDto.getCorpName(), address.get(), companyDto.getDirectorName()))
            return new ResponseApi("The corporation already exists at this address", false);
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(address.get());
        companyRepository.save(company);
        return new ResponseApi("Company data is edited",true);
    }

    /**
     * Deleting company by id
     * @param id
     * @return ResponseApi - response message and status of deleting result
     */
    public ResponseApi deleteById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty())
            return new ResponseApi("Company not found", false);
        companyRepository.deleteById(id);
        return new ResponseApi("Company is deleted", true);
    }
}
