package com.healsmart.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healsmart.dtos.MedicineAssignedDTO;
import com.healsmart.dtos.ResponseDTO;
import com.healsmart.services.MedicineService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
   @Autowired
   private MedicineService medicineService;
   
   @RolesAllowed({"ROLE_ADMIN","ROLE_DOCTOR"})
	@GetMapping("getAllMedicines")
	public ResponseEntity<?> getAllMedicine() {
		List<MedicineAssignedDTO> allMedicines = medicineService.getAllMedicines();
		if (allMedicines != null)
			return ResponseDTO.success(allMedicines);
		return ResponseDTO.error("NO_LIST_FOUND");
	}
   
   //************************************************************
	@RolesAllowed({"ROLE_ADMIN"})
	@PostMapping("/addMedicine")
	public ResponseEntity<?> addMedicine(@RequestBody MedicineAssignedDTO medicineData) {
		Long updateCount = medicineService.addMedicine(medicineData);
		if (updateCount == 1)
			return ResponseDTO.success("MEDICINE_ADDED");
		return ResponseDTO.success("FAILED");
	}
	
	//************************************************************
	@RolesAllowed({"ROLE_DOCTOR","ROLE_ADMIN"})
	@GetMapping("/removeMedicine/{id}") 
	public ResponseEntity<?> removeMedicine(@PathVariable("id") Long medicineId) {
		medicineService.removeMedicine(medicineId);
		return ResponseDTO.success("MEDICINE_REMOVED");
	}

}
