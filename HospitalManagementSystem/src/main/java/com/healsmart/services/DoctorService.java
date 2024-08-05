package com.healsmart.services;

import java.util.List;

import com.healsmart.dtos.DoctorDTO;
import com.healsmart.dtos.PatientDTO;

public interface DoctorService {
   
    List<DoctorDTO> getAllDoctors();
    void updatePatientDetails(PatientDTO patientDTO);
    
}
