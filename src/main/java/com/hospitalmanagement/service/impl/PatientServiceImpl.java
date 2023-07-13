package com.hospitalmanagement.service.impl;

import com.hospitalmanagement.entities.Patient;
import com.hospitalmanagement.enums.PatientStatus;
import com.hospitalmanagement.exceptions.ResourceNotFoundException;
import com.hospitalmanagement.payload.PatientDto;
import com.hospitalmanagement.repository.PatientRepository;
import com.hospitalmanagement.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    ModelMapper patientMapper;


    @Override
    public PatientDto admitPatient(PatientDto patientDTO) {
        Patient patient = mapToEntity(patientDTO);
        patient.setStatus(PatientStatus.ADMITTED);
        Patient savedPatient = patientRepository.save(patient);
        PatientDto patientDto = mapToDto(savedPatient);
        return patientDTO;
    }

    @Override
    public List<PatientDto> getAdmittedPatients() {
        List<Patient> admittedPatients = patientRepository.findByStatus(PatientStatus.ADMITTED);
        return admittedPatients.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<PatientDto> fetchAllPatients() {
        List<Patient> allPatients = patientRepository.findAll();
        return allPatients.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void dischargePatient(Long patientId) {
        Patient dischargePatient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("Patient not found with Id : " + patientId)
        );

            dischargePatient.setStatus(PatientStatus.DISCHARGED);
            patientRepository.save(dischargePatient);

    }

    private Patient mapToEntity(PatientDto patientDTO) {
        Patient patient = patientMapper.map(patientDTO, Patient.class);
        return patient;
    }


    private PatientDto mapToDto(Patient patient) {
        PatientDto dto = patientMapper.map(patient, PatientDto.class);
        return dto;
    }
}

