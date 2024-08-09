package com.healsmart.services;

import java.util.List;

import com.healsmart.dtos.EmployeeDTO;
import com.healsmart.entities.User;

public interface UserService {
	public List<User> findAllUsers();
	
	public User findUserById(Long userId);
	
	public User findUserByEmail(String email);
	
	public EmployeeDTO getUserByEmailAndPassword(EmployeeDTO employeeDTO);
	
	public Boolean checkIfEmailExists(EmployeeDTO employeeDTO);
	
	public Boolean checkByEmailAndSecurity(EmployeeDTO employeeDTO);
	
	public Boolean updatePassword(EmployeeDTO employeeDTO);
	
}
