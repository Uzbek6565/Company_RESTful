package com.example.task2_1_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseApi {
    private String message;
    private boolean success;
    private Object object;

    public ResponseApi(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
