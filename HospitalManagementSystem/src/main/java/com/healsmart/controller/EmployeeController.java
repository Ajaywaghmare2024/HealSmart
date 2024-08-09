package com.healsmart.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.healsmart.dtos.EmployeeDTO;
import com.healsmart.dtos.ResponseDTO;
import com.healsmart.services.EmployeeService;

@CrossOrigin
@RestController
@RolesAllowed("ROLE_ADMIN")
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
 //get the all emlpyee List
	@GetMapping("/getAllEmployees")
	public ResponseEntity<?> getAll() {
		List<EmployeeDTO> result = employeeService.getAllEmployees();
		return ResponseDTO.success(result);
	}
	

	@PostMapping("/addEmployee")
	public @ResponseBody String addEmployee(@RequestBody EmployeeDTO userData) {
		System.out.println("recieved password : " + userData.getPassword());
		employeeService.addEmployee(userData);
		return "employee added success";

	}
	

	@PutMapping("/updateEmployee")
	public @ResponseBody String updateEmployee(@RequestBody EmployeeDTO userData) {
		System.out.println("recieved password : " + userData.getPassword());
		employeeService.updateEmployee(userData);
		return "employee added success";

	}
	
	
     @DeleteMapping("/deleteEmployee")
	public ResponseEntity<?> deleteEmployee(@RequestBody EmployeeDTO userData) {
		int deletedNo = employeeService.deleteUserByCellNoAndUserId(userData);
		if (deletedNo == 1)
			return ResponseDTO.success("deleted_success");
		return ResponseDTO.error("employee_not_deleted");

	}

}
