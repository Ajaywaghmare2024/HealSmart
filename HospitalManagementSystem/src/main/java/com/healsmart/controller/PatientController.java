package com.healsmart.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healsmart.dtos.MedicineAssignedDTO;
import com.healsmart.dtos.PatientChargesDTO;
import com.healsmart.dtos.PatientDTO;
import com.healsmart.dtos.ResponseDTO;
import com.healsmart.services.PatientService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/patient")

public class PatientController {
    @Autowired
    PatientService patientService;
    
    
    @RolesAllowed("ROLE_RECEPTION")
	@PostMapping("/addPatient")
	public ResponseEntity<?> addPatient(@RequestBody PatientDTO patientData) {
		Long
		updateCount = patientService.addPatient(patientData);
		if (updateCount == 1)
			return ResponseDTO.success("added");
		return ResponseDTO.error("adding failed");
	}

	@GetMapping("/getAllPatients")
	@RolesAllowed({"ROLE_RECEPTION","ROLE_ACCOUNTANT"})
	public ResponseEntity<?> getAllPatients() {
		List<PatientDTO> patients = patientService.getAllPatients();

		return ResponseDTO.success(patients);
	}
	
	@RolesAllowed({"ROLE_RECEPTION","ROLE_ACCOUNTANT","ROLE_PATIENT","ROLE_DOCTOR"})
	@GetMapping("/getPatient/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable("id") Long patientId) {
		PatientDTO patient = patientService.getPatientById(patientId);
		if (patient != null)
			return ResponseDTO.success(patient);
		return ResponseDTO.success("failed invalid patient id");

	}
	

	@RolesAllowed({"ROLE_RECEPTION"})
	@DeleteMapping("/removePatient/{id}")
	public ResponseEntity<?> deletePatientById(@PathVariable("id") Long patientId) {
		patientService.removePatientById(patientId);
		return ResponseDTO.success("success removed");

	}
	

	@RolesAllowed({"ROLE_RECEPTION"})
	@PostMapping("/updatePatient")
	public void updatePatient(@RequestBody PatientDTO patientData) {
		patientService.updatePatientDetails(patientData);
	}
	
	
	@RolesAllowed({"ROLE_RECEPTION","ROLE_PATIENT","ROLE_DOCTOR"})
	@GetMapping("/getMedicines/{id}")
	public ResponseEntity<?> getMedicineByPatId(@PathVariable("id") Long patientId) {
		List<MedicineAssignedDTO> medicines = patientService.getMedicineByPatId(patientId);
		if (medicines != null)
			return ResponseDTO.success(medicines);
		return ResponseDTO.success("failed invalid medicines id");

	}
	@RolesAllowed({"ROLE_RECEPTION","ROLE_PATIENT","ROLE_DOCTOR","ROLE_ACCOUNTANT"})
	@GetMapping("/getCharges/{id}")
	public ResponseEntity<?> getChargesByPatId(@PathVariable("id") Long patientId) {
		PatientChargesDTO patientTotalCharges = patientService.calculateChargesByPatId(patientId);
		if (patientTotalCharges != null)
			return ResponseDTO.success(patientTotalCharges);
		return ResponseDTO.error("INVALID_PATIENT_ID");

	}
	
	
	@RolesAllowed({"ROLE_ACCOUNTANT"})
	@PostMapping("/updatePatientPaymentStatus")
	public void updatePatientPaymentStatus(@RequestBody PatientDTO patientData) {
		patientService.updatePaymentStatusByPatId(patientData);
	}
	
	
	@RolesAllowed({"ROLE_RECEPTION"})
	@PostMapping("/bedExists")
	public ResponseEntity<?> checkIfBedIsFree(@RequestBody PatientDTO bedData) {
		Boolean bedStatus = patientService.checkIfBedAvailable(bedData);
		if (bedStatus == true)
			return ResponseDTO.success("BED_NOT_AVAILABLE");
		else
			return ResponseDTO.success("BED_AVAILABLE");

	}

}
