package com.healsmart.services;

import com.healsmart.dtos.MedicineAssignedDTO;

public interface MedicineAssignedService {
	
    public void addMedicineToPatient(MedicineAssignedDTO medicineAssignDTO);
    public void removeMedicineOfPatient(Long medicineAssignId);
} 
