package com.healsmart.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healsmart.dtos.MedicineAssignedDTO;
import com.healsmart.dtos.ResponseDTO;
import com.healsmart.services.MedicineAssignedService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medicineAssigned")
@RolesAllowed({"ROLE_DOCTOR","ROLE_ADMIN"})
public class MedicineAssingedController {
	@Autowired
	private MedicineAssignedService medicineAssignedService;
	

	@PostMapping("/addMedicineToPatient")
	public void addMedicineToPatient(@RequestBody MedicineAssignedDTO assignedMedicine) {
		medicineAssignedService.addMedicineToPatient(assignedMedicine);

	}

	@DeleteMapping("/removeMedicineAssigned/{id}")
	public ResponseEntity<?> deletePatientById(@PathVariable("id") Long medicineAssignId) {
		medicineAssignedService.removeMedicineOfPatient(medicineAssignId);
		return ResponseDTO.success("success removed");

	}


}
