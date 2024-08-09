package com.healsmart.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healsmart.dtos.DoctorDTO;
import com.healsmart.dtos.DoctorVisitDTO;
import com.healsmart.dtos.PatientDTO;
import com.healsmart.dtos.ResponseDTO;
import com.healsmart.services.DoctorService;
import com.healsmart.services.DoctorVisitService;
import com.healsmart.services.PatientService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	 DoctorService doctorService;
	@Autowired
	private DoctorVisitService doctorVisitService;
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/getAllDoctors")
	public ResponseEntity<?> getAllDoctors() {
	    List<DoctorDTO> doctors = doctorService.getAllDoctors();
	    return ResponseDTO.success(doctors);
	}

	
	@RolesAllowed({"ROLE_DOCTOR"})
	@PostMapping("/addPrescription")
	public ResponseEntity<?> updatePatient(@RequestBody PatientDTO patientData) {
		
		doctorService.updatePatientDetails(patientData);
		return ResponseDTO.success("Patient Details Updated Successfully");
	}
	

	@RolesAllowed({"ROLE_DOCTOR"})
	@PostMapping("/increaseVisitCount")
	public ResponseEntity<?> increaseVisitCount(@RequestBody DoctorVisitDTO visitData) {
		doctorVisitService.increaseVisitCount(visitData);
		return ResponseDTO.success("increamented");
	}
	

	@RolesAllowed({"ROLE_DOCTOR"})
	@GetMapping("/getPatients/{id}")
	public ResponseEntity<?> getPatientsOfDoctor(@PathVariable Long id){
		List<PatientDTO> patientList=patientService.getPatientsOfDoctor(id);
		return ResponseDTO.success(patientList);
	}
	

}
