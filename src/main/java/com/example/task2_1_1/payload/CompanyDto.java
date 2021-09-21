package com.example.task2_1_1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CompanyDto {

    @NotNull(message = "Corporation name can not be empty")
    private String corpName;

    @NotNull(message = "Director name can not be empty")
    private String directorName;

    @NotNull(message = "Address is not selected")
    private Integer addressId;
}
