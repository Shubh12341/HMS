package com.hospitalmanagement.repository;


import com.hospitalmanagement.entities.Patient;
import com.hospitalmanagement.enums.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByStatus(PatientStatus status);
    List<Patient> findAllByOrderByAdmitDateDesc();
}
