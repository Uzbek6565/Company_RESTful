package com.example.task2_1_1.controller;

import com.example.task2_1_1.entity.Address;
import com.example.task2_1_1.payload.AddressDto;
import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * Creating new address
     *
     * @param addressDto
     * @return ResponseApi - response message and status of creating result
     */
    @PostMapping
    public HttpEntity<ResponseApi> create(@Valid @RequestBody AddressDto addressDto) {
        ResponseApi responseApi = addressService.create(addressDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(responseApi);
    }

    /**
     * Getting all addresses
     *
     * @return List<Address> - List of Addresses
     */
    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAll());
    }

    /**
     * Getting Address by ID
     *
     * @param id
     * @return ResponseApi - response message, status and Address object of getting result
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        ResponseApi responseApi = addressService.getById(id);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(responseApi);
    }

    /**
     * Editing address data by ID
     *
     * @param id
     * @param addressDto
     * @return ResponseApi - response message and status of editing result
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi> edit(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto) {
        ResponseApi responseApi = addressService.edit(id, addressDto);
        return ResponseEntity.status(responseApi.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST).body(responseApi);
    }

    /**
     * Deleting address by ID
     * @param id
     * @return ResponseApi - response message and status of deleting result
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> delete(@PathVariable Integer id) {
        ResponseApi responseApi = addressService.delete(id);
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
