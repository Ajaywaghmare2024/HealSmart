package com.healsmart.services;

import java.util.List;

import com.healsmart.dtos.MedicineAssignedDTO;

public interface MedicineService {
	
   public List<MedicineAssignedDTO> getAllMedicines();
   
   public Long addMedicine(MedicineAssignedDTO medicineAssignedDTO);
   
   public void removeMedicine(Long medicineId);
}
