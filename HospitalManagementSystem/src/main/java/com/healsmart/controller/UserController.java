package com.healsmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healsmart.dtos.EmployeeDTO;
import com.healsmart.dtos.ResponseDTO;
import com.healsmart.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/emailExists")
	public ResponseEntity<?> checkIfEmailExists(@RequestBody EmployeeDTO useData ){
		Boolean emailExists=userService.checkIfEmailExists(useData);
		if(emailExists==true)
		return ResponseDTO.success("DUPLICATE_EMAIL");
		else
		return ResponseDTO.success("UNIQUE_EMAIL");
	}
	
	@PostMapping("/validateSecurityAnswer")
	public ResponseEntity<?> checkIfUserExistByEmailAndSecurity(@RequestBody EmployeeDTO useData ){
		Boolean emailExists=userService.checkByEmailAndSecurity(useData);
		if(emailExists==true)
		return ResponseDTO.success("VALID");
		else
		return ResponseDTO.success("INVALID");
	}
	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody EmployeeDTO useData ){
		Boolean emailExists=userService.updatePassword(useData);
		if(emailExists==true)
		return ResponseDTO.success("PASSWORD_CHANGED");
		else
		return ResponseDTO.success("INVALID");
	}
	
	

}
