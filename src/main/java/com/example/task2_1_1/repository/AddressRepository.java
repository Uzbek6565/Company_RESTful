package com.example.task2_1_1.repository;

import com.example.task2_1_1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByStreetAndHomeNumber(String street, Integer homeNumber);
}
