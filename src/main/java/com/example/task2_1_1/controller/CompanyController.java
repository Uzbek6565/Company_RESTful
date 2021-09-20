package com.example.task2_1_1.controller;

import com.example.task2_1_1.entity.Company;
import com.example.task2_1_1.payload.CompanyDto;
import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    /**
     * Creating company
     * @param companyDto
     * @return ResponseApi - response message and status of creating result
     */
    @PostMapping
    public ResponseEntity<ResponseApi> create(@RequestBody CompanyDto companyDto) {
        ResponseApi responseApi = companyService.create(companyDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Getting all companies
     * @return List<Company>
     */
    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.status(200).body(companyService.getAll());
    }

    /**
     * Getting company by id
     * @param id
     * @return Company
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Integer id) {
        Company company = companyService.getById(id);
        return ResponseEntity.status(company != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(company);
    }

    /**
     * Updatind company data
     * @param id
     * @param companyDto
     * @return ResponseApi - response message and status of updating result
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi> update(@PathVariable Integer id, @Valid @RequestBody CompanyDto companyDto) {
        ResponseApi responseApi = companyService.update(id, companyDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Deleting company by id
     * @param id
     * @return ResponseApi - response message and status of deleting result
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteById(@PathVariable Integer id){
        ResponseApi responseApi = companyService.deleteById(id);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST).body(responseApi);
    }

    /**
     * Validation message
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
