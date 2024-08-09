package com.healsmart.services;

import java.util.List;

import com.healsmart.dtos.MedicineAssignedDTO;
import com.healsmart.dtos.PatientChargesDTO;
import com.healsmart.dtos.PatientDTO;

public interface PatientService {
	// add the patient
    public Long addPatient(PatientDTO patientDTO);
    // get all patient List
    public List<PatientDTO> getAllPatients();
    // get patientBy id
    public PatientDTO getPatientById(Long patId);
   // getMedicine By Pat id 
    public List<MedicineAssignedDTO> getMedicineByPatId(Long patId);
    
    // update the details of the patient
    public void updatePatientDetails(PatientDTO patientDTO);
    
    // delete the patient by id
    public int removePatientById(Long patId);
    
    // charges calculation 
    
    public PatientChargesDTO calculateChargesByPatId( Long patId);
    
    // update paymentStatus by pat Id
    
    public void updatePaymentStatusByPatId(PatientDTO patientData);
    
    
	//check if bed alloted exits
	public Boolean checkIfBedAvailable(PatientDTO bedData);
    
	public List<PatientDTO> getPatientsOfDoctor(Long PatientId);
    
    
    
}
