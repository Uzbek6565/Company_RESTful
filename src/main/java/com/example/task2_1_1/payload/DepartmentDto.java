package com.example.task2_1_1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {

    @NotNull(message = "Department name can not be empty")
    private String name;

    private Integer companyId;
}
