package com.healsmart.services;

import java.util.List;
import static com.healsmart.dtos.EmployeeDTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healsmart.dtos.EmployeeDTO;
import com.healsmart.entities.User;
import com.healsmart.repositories.EmployeeRepository;
import com.healsmart.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Long userId) {
		return userRepository.getById(userId);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	
	// get user by email and password
	@Override
	public EmployeeDTO getUserByEmailAndPassword(EmployeeDTO employeeDTO) {
		EmployeeDTO userCreated=new EmployeeDTO();
		User validUser=userRepository.findByEmail(employeeDTO.getEmail());
		if(validUser!=null) 
			return userCreated=createUser(validUser);
		return null;
	}
     // check email is exists or unique
	@Override
	public Boolean checkIfEmailExists(EmployeeDTO employeeDTO) {
	    return userRepository.existsByEmail(employeeDTO.getEmail());
	}

	@Override
	public Boolean checkByEmailAndSecurity(EmployeeDTO employeeDTO) {
		User user=userRepository.findByEmail(employeeDTO.getEmail());
		if(user.getSecurityAnswer().equals(employeeDTO.getSecurityAnswer())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean updatePassword(EmployeeDTO employeeDTO) {
		User user = userRepository.findByEmail(employeeDTO.getEmail());
		if (user.getSecurityAnswer().equals(employeeDTO.getSecurityAnswer())) {
			user.setPassword(employeeDTO.getPassword());
			User updatedReturnUser = userRepository.save(user);
			if (updatedReturnUser != null)
				return true;

		}
		return false;

	}

}
