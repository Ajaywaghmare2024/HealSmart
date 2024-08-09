package com.healsmart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healsmart.custom_exception.NoSuchMedicineExistsException;
import com.healsmart.dtos.MedicineAssignedDTO;
import com.healsmart.repositories.EmployeeRepository;
import com.healsmart.repositories.MedicineAssignedRepository;
import com.healsmart.repositories.MedicineRepository;
import com.healsmart.repositories.UserRepository;
import com.healsmart.repositories.WardRepository;

@Service
@Transactional
public class MedicineAssignedServiceImpl implements MedicineAssignedService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private MedicineAssignedRepository medicineAssignedRepository;
	
	

	@Override
	public void addMedicineToPatient(MedicineAssignedDTO medicineAssignDTO) throws NoSuchMedicineExistsException {
		medicineAssignedRepository.addIntoMedicineAssigned
		       (medicineAssignDTO.getPatId(),
				medicineAssignDTO.getMedicineId(),
				medicineAssignDTO.getMedicinePrescription(),
				medicineAssignDTO.getMedicineQty()); 
		
	}

	@Override
	public void removeMedicineOfPatient(Long medicineAssignId) {
		medicineAssignedRepository.deleteById(medicineAssignId);
		
	}

}
