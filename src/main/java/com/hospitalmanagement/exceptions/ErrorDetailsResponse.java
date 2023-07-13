package com.hospitalmanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetailsResponse {
    private Date timestamp;

    private String message;

    private  String details;
}
