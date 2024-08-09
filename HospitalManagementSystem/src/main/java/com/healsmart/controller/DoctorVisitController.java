package com.healsmart.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healsmart.dtos.DoctorVisitDTO;
import com.healsmart.dtos.ResponseDTO;
import com.healsmart.services.DoctorVisitService;
@CrossOrigin("*")
@RestController
@RolesAllowed("ROLE_DOCTOR")
@RequestMapping("/api/doctorVisit")
public class DoctorVisitController {
	@Autowired
	private DoctorVisitService doctorVisitService;

	@PostMapping("/addVisit")
	public ResponseEntity<?> addWard(@RequestBody DoctorVisitDTO visitData) {
		Long updateCount = doctorVisitService.addVisit(visitData);
		if (updateCount == 1)
			return ResponseDTO.success("VISIT_ADDED");
		return ResponseDTO.success("FAILED");
	}


}
