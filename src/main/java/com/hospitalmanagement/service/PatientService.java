package com.hospitalmanagement.service;


import com.hospitalmanagement.payload.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto admitPatient(PatientDto patientDTO);
    List<PatientDto> getAdmittedPatients();
    List<PatientDto> fetchAllPatients();
    void dischargePatient(Long patientId);
}
