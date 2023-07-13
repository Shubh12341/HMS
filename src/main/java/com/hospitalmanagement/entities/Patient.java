package com.hospitalmanagement.entities;


import com.hospitalmanagement.enums.PatientStatus;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "patient_name")
    private String patientName;

    @Min(value = 1)
    @Column(name = "age")
    private int age;

    @Column(name = "room")
    private int room;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "admit_date")
    private LocalDate admitDate;

    @Column(name = "expenses")
    private BigDecimal expenses;
    @Enumerated(EnumType.STRING)

    @Column(name = "status")
    private PatientStatus status;

}
