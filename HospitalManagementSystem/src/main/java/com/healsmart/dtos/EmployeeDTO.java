package com.healsmart.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.healsmart.entities.Employee;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class EmployeeDTO {
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String role;
	private String cellNo;
	private String securityQuestion;
	private String securityAnswer;
	// to generate jwt token
	private String token;

	// ****************************************

	private Long empId;
	private LocalDate dob;
	private LocalDate hireDate;
	private double salary;
	private double doctorCharges;
	private Long patId; // required if role is patient
	private Long doctorId;
//*******************************************
	
	public static List<EmployeeDTO> createEmployee(List<Employee> employees){
		List<EmployeeDTO> employeeDetails= new ArrayList<>();
		for(Employee e:employees) {
			EmployeeDTO emp= new EmployeeDTO();
			emp.setFirstName(e.getUser().getFirstName());
			emp.setLastName(e.getUser().getLastName());
			emp.setRole(e.getUser().getRole());
			emp.setCellNo(e.getUser().getCellNo());
			emp.setEmpId(e.getEmp_id());
			emp.setDob(e.getDob());
			emp.setHireDate(e.getHireDate());
			emp.setSalary(e.getSalary());
			emp.setEmail(e.getUser().getEmail());
			
			employeeDetails.add(emp);
		}
		
		return employeeDetails;
	}
	
}
