package com.healsmart.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.healsmart.entities.Employee;
import com.healsmart.entities.User;

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
	// *************************

	@JsonProperty(access = Access.WRITE_ONLY)
	public Employee getEmployeeFromData() {
		Employee employee = new Employee(dob, hireDate, salary);
		User user = new User(firstName, lastName, email, password, role, cellNo, securityQuestion, securityAnswer);
		employee.setUser(user);
		return employee;

	}
//*******************************************

	public static List<EmployeeDTO> createEmployee(List<Employee> employees) {
		List<EmployeeDTO> employeeDetails = new ArrayList<>();
		for (Employee e : employees) {
			EmployeeDTO emp = new EmployeeDTO();
			emp.setFirstName(e.getUser().getFirstName());
			emp.setLastName(e.getUser().getLastName());
			emp.setRole(e.getUser().getRole());
			emp.setCellNo(e.getUser().getCellNo());
			emp.setEmpId(e.getEmpId());
			emp.setDob(e.getDob());
			emp.setHireDate(e.getHireDate());
			emp.setSalary(e.getSalary());
			emp.setEmail(e.getUser().getEmail());

			employeeDetails.add(emp);
		}

		return employeeDetails;
	}
	// ****************create a user **************

	public static EmployeeDTO createUser(User user) {

		EmployeeDTO validUser = new EmployeeDTO();
		validUser.setEmail(user.getEmail());
		validUser.setRole(user.getRole());
		validUser.setPassword(user.getPassword());
		if (user.getRole().equals("patient")) {
			validUser.setPatId(user.getPatient().getPatientId());
		}
		if (user.getRole().equals("doctor"))
			validUser.setDoctorId(user.getEmployee().getDoctor().getDoctorId());
		return validUser;
	}
     // **************to update an employee
	
	public static Employee updateEmployee(Long userId,EmployeeDTO userData) {
		Employee updatedEmployee=new Employee();
		User corruspondingUser=new User();
		corruspondingUser.setUserId(userId);
		corruspondingUser.setFirstName(userData.getFirstName());
		corruspondingUser.setLastName(userData.getLastName());
		corruspondingUser.setRole(userData.getRole());
		updatedEmployee.setEmpId(userData.getEmpId());
		
		return updatedEmployee;
		
	}
}
