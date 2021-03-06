package com.example.task2_1_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class AddressDto {

    @NotNull(message = "Street can not be empty")
    private String street;

    @NotNull(message = "Home number can not be empty")
    private Integer homeNumber;
}
