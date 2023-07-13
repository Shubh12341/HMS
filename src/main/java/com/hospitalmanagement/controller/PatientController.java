package com.hospitalmanagement.controller;

import com.hospitalmanagement.payload.PatientDto;
import com.hospitalmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    //localhost:8080/api/patients/admit
    @PostMapping("/admit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PatientDto> admitPatient(@RequestBody @Valid PatientDto patientDto) {
        PatientDto admittedPatient = patientService.admitPatient(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(admittedPatient);
    }

    //localhost:8080/api/patients/admitted
    @GetMapping("/admitted")
    public ResponseEntity<List<PatientDto>> getAdmittedPatients() {
        List<PatientDto> admittedPatients = patientService.getAdmittedPatients();
        return ResponseEntity.ok(admittedPatients);
    }

    //localhost:8080/api/patients/all
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> allPatients = patientService.fetchAllPatients();
        return ResponseEntity.ok(allPatients);
    }

    //localhost:8080/api/patients/discharge/id
    @PutMapping("/discharge/{patientId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> dischargePatient(@PathVariable Long patientId) {
        patientService.dischargePatient(patientId);
        return ResponseEntity.noContent().build();
    }
}
