package com.healsmart.services;

import static com.healsmart.dtos.DoctorDTO.createDoctorsList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healsmart.custom_exception.NoSuchPatientFoundException;
import com.healsmart.dtos.DoctorDTO;
import com.healsmart.dtos.PatientDTO;
import com.healsmart.entities.Doctor;
import com.healsmart.repositories.DoctorRepository;
import com.healsmart.repositories.EmployeeRepository;
import com.healsmart.repositories.PatientRepository;
import com.healsmart.repositories.UserRepository;
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	

	@Override
	public List<DoctorDTO> getAllDoctors() {
		List<Doctor> doctors=doctorRepository.findAll();
		List<DoctorDTO> doctorDetail=createDoctorsList(doctors);
		
		return doctorDetail;
		
	}

	@Override
	public void updatePatientDetails(PatientDTO patientDTO) throws NoSuchPatientFoundException{
		Long updateCount;
		if(patientRepository.existsById(patientDTO.getPatId()))
			updateCount=patientRepository.updatePatientPrescription(patientDTO.getPrescription(),patientDTO.getPatId());
		else
			throw new NoSuchPatientFoundException("Patient with id"+patientDTO.getPatId()+"does Not exists");
		
	}
  
}
