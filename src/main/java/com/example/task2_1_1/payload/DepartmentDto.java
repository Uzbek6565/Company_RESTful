package com.example.task2_1_1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {

    @NotNull(message = "Department name can not be empty")
    private String name;

    @NotNull(message = "Company is not selected")
    private Integer companyId;
}
