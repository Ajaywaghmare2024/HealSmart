package com.healsmart.services;

import java.util.List;

import com.healsmart.dtos.EmployeeDTO;

public interface EmployeeService {
   public Long addEmployee(EmployeeDTO empDTO);
   
   List<EmployeeDTO> getAllEmployees();
   
   public void updateEmployee(EmployeeDTO employeeDTO);
   
   public void deleteUserByCellNoAndUserId(EmployeeDTO userData);
   
}
