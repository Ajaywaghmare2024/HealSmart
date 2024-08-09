package com.healsmart.services;

import java.util.List;
import  static com.healsmart.dtos.MedicineAssignedDTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healsmart.dtos.MedicineAssignedDTO;
import com.healsmart.entities.Medicine;
import com.healsmart.repositories.MedicineRepository;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService{
	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public List<MedicineAssignedDTO> getAllMedicines() {
		
		List<Medicine> medicine=medicineRepository.findAll();
		List<MedicineAssignedDTO> medicinesTosend=createAllMedicineList(medicine);
		return medicinesTosend;
	}

	@Override
	public Long addMedicine(MedicineAssignedDTO medicineAssignedDTO) {
		
		return medicineRepository.insertIntoMedicineTable(0, medicineAssignedDTO.getMedicineName(), medicineAssignedDTO.getMedicinePrice());
	}

	@Override
	public void removeMedicine(Long medicineId) {
		medicineRepository.deleteById(medicineId);
		
	}

}
