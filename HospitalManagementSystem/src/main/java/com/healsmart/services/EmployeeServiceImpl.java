package com.healsmart.services;

import static com.healsmart.dtos.EmployeeDTO.createEmployee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healsmart.custom_exception.EmployeeAlreadyExistsException;
import com.healsmart.custom_exception.NoSuchEmployeeExistsException;
import com.healsmart.dtos.EmployeeDTO;
import com.healsmart.entities.Employee;
import com.healsmart.entities.Patient;
import com.healsmart.entities.User;
import com.healsmart.repositories.DoctorRepository;
import com.healsmart.repositories.EmployeeRepository;
import com.healsmart.repositories.UserRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	

	@Override
	public Long addEmployee(EmployeeDTO empDTO) {
		if(!userRepository.existsByEmail(empDTO.getEmail())) {
			if(empDTO.getRole().equalsIgnoreCase("doctor")) {
				userRepository.insertIntoUsers(0, empDTO.getFirstName(), empDTO.getLastName(), empDTO.getEmail(), empDTO.getPassword(), empDTO.getCellNo(), empDTO.getRole(), empDTO.getSecurityQuestion(), empDTO.getSecurityAnswer());
				User user=userRepository.findByEmail(empDTO.getEmail());//to get userId
				System.out.println("user id : "+user.getUserId());
				Long updateCount=employeeRepository.insertIntoEmployeesTable(0, user.getUserId(), empDTO.getDob(), empDTO.getHireDate(), empDTO.getSalary());
					Long empId=employeeRepository.getEmpIdByEmail(empDTO.getEmail());
					System.out.println("emp id : "+empId);
					doctorRepository.insertIntoDoctorTable(0, empId, empDTO.getDoctorCharges());
				return updateCount;
			}
			else {
				userRepository.insertIntoUsers(0, empDTO.getFirstName(), empDTO.getLastName(), empDTO.getEmail(), empDTO.getPassword(), empDTO.getCellNo(), empDTO.getRole(), empDTO.getSecurityQuestion(), empDTO.getSecurityAnswer());
				User user=userRepository.findByEmail(empDTO.getEmail());//to get userId
				Long updateCount=employeeRepository.insertIntoEmployeesTable(0, user.getUserId(), empDTO.getDob(), empDTO.getHireDate(), empDTO.getSalary());
				return updateCount;
			}
			
		}
		else {
			throw new EmployeeAlreadyExistsException("employee with email "+empDTO.getEmail()+" already exists!!!");
		}
		
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees=employeeRepository.findAll();
		List<EmployeeDTO> employeeDetails =createEmployee(employees);
		return employeeDetails;
	}

	@Override
	public void updateEmployee(EmployeeDTO employeeDTO) throws NoSuchEmployeeExistsException{
		if(employeeRepository.existsById(employeeDTO.getEmpId()) ) {
			Employee employeeToUpdate =employeeRepository.getById(employeeDTO.getEmpId());
			
			Long updateCount=userRepository.updateFirstNameLastNameDobCellNo
					(employeeDTO.getFirstName(), 
					employeeDTO.getLastName(), 
					employeeDTO.getDob(), 
					employeeDTO.getCellNo(), 
					employeeToUpdate.getUser().getUserId());
			System.out.println("updated row : "+ updateCount);
			
		}else {
			throw new NoSuchEmployeeExistsException("Employee with id = "+employeeDTO.getEmpId()+" does not exist");
		}
		
	}

	@Override
	public int deleteUserByCellNoAndUserId(EmployeeDTO userData) throws NoSuchEmployeeExistsException{
            if(employeeRepository.existsById(userData.getEmpId()) ) {
			
			Employee employeeToDelete=employeeRepository.getById(userData.getEmpId());
			
			if(employeeToDelete.getUser().getRole().equals("doctor")) {
				System.out.println("---nside doctor delete");
				List<Patient> patients = employeeToDelete.getDoctor().getPatients();
				if(patients.isEmpty()) {
					employeeRepository.deleteById(userData.getEmpId());
					
				}
			}else {
				employeeRepository.deleteById(userData.getEmpId());
				System.out.println("no of employees deleted : ");
				 
			}
			
		}else {
			throw new NoSuchEmployeeExistsException("Employee with id = "+userData.getEmpId()+" does not exist");
		}
       return 1;
		
		
	}

}
