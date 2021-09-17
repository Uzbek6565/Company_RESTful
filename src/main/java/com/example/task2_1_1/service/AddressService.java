package com.example.task2_1_1.service;

import com.example.task2_1_1.entity.Address;
import com.example.task2_1_1.payload.AddressDto;
import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * Creating new address
     *
     * @param addressDto
     * @return ResponseApi - response message and status of creating result
     */
    public ResponseApi create(AddressDto addressDto) {
        if (addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber()))
            return new ResponseApi("Address already exists", false);
        Address address = new Address(null, addressDto.getStreet(), addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ResponseApi("Address is created", true);
    }

    /**
     * Getting all addresses
     *
     * @return List<Address> - List of Addresses
     */
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    /**
     * Getting Address by ID
     *
     * @param id
     * @return ResponseApi - response message, status and Address object of getting result
     */
    public ResponseApi getById(Integer id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.map(address -> new ResponseApi("Address by ID", true, address)).orElseGet(() -> new ResponseApi("Address not found", false, null));
    }

    /**
     * Editing address data by ID
     *
     * @param id
     * @param addressDto
     * @return ResponseApi - response message and status of editing result
     */
    public ResponseApi edit(Integer id, AddressDto addressDto) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty())
            return new ResponseApi("Address not found", false);
        if (addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber()))
            return new ResponseApi("Address already exists", false);

        Address address = addressOptional.get();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new ResponseApi("Address is edited", true, addressOptional.get());
    }

    /**
     * Deleting address by ID
     * @param id
     * @return ResponseApi - response message and status of deleting result
     */
    public ResponseApi delete(Integer id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty())
            return new ResponseApi("Address not found", false);
        addressRepository.deleteById(id);
        return new ResponseApi("Address is deleted", true);
    }
}
