package com.hospitalmanagement.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDto {

        @NotBlank(message = "Name is required")
        private String patientName;

        @Min(value = 1, message = "Age must be a positive number")
        private int age;

        @NotBlank(message = "Room is required")
        private String room;

        @NotBlank(message = "Doctor name is required")
        private String doctorName;

        @FutureOrPresent(message = "Admit date must be in the future or present")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @NotNull(message = "Admit date is required")
        private LocalDate admitDate;

        @DecimalMin(value = "0.0", inclusive = false, message = "Expenses must be a positive number")
        private BigDecimal expenses;

        @NotBlank(message = "Status is required")
        private String status;


    }

