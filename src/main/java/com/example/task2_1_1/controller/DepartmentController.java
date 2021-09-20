package com.example.task2_1_1.controller;

import com.example.task2_1_1.entity.Department;
import com.example.task2_1_1.payload.DepartmentDto;
import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.repository.CompanyRepository;
import com.example.task2_1_1.repository.DepartmentRepository;
import com.example.task2_1_1.service.DepartmentService;
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
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ResponseApi> create(@Valid @RequestBody DepartmentDto departmentDto) {
        ResponseApi responseApi = departmentService.create(departmentDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id){
        Department department = departmentService.getById(id);
        return ResponseEntity.status(department !=null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi> update(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto){
        ResponseApi responseApi = departmentService.update(id, departmentDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(responseApi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteById(@PathVariable Integer id){
        ResponseApi responseApi = departmentService.deleteById(id);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST).body(responseApi);
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
