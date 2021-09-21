package com.example.task2_1_1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {
    @NotNull(message = "Worker name can not be empty")
    private String name;

    @NotNull(message = "Phone number can not be empty")
    private Integer phoneNumber;

    @NotNull(message = "Address is not selected")
    private Integer addressId;

    @NotNull(message = "Department is not selected")
    private Integer departmentId;

}
