package com.healsmart.dtos;

import java.util.ArrayList;
import java.util.List;

import com.healsmart.entities.Medicine;
import com.healsmart.entities.MedicineAssigned;

import lombok.Data;

@Data
public class MedicineAssignedDTO {
    private Long patId;
    private Long medicineId;
    private String medicinePrescription;
    private int medicineQty;
    private String medicineName;
    private double medicinePrice;
    private Long medicineAssignedId;
    
	public static List<MedicineAssignedDTO> createMedicineListForPatient(List<MedicineAssigned> medicines){
		List<MedicineAssignedDTO> medicineDtoList=new ArrayList<MedicineAssignedDTO>();
		
		for(MedicineAssigned medicineAssigned:medicines) {
			MedicineAssignedDTO medicineAssignedDto=new MedicineAssignedDTO();
			medicineAssignedDto.setMedicineId(medicineAssigned.getMedicine().getMedicineId());
			medicineAssignedDto.setMedicineName(medicineAssigned.getMedicine().getMedicineName());
			medicineAssignedDto.setMedicinePrice(medicineAssigned.getMedicine().getPrice());
			medicineAssignedDto.setMedicineQty(medicineAssigned.getMedicineQty());
			medicineAssignedDto.setPatId(medicineAssigned.getPatient().getPatientId());
			medicineAssignedDto.setMedicinePrescription(medicineAssigned.getPrescription());
			medicineAssignedDto.setMedicineAssignedId(medicineAssigned.getMedicineAssignId());
			
			medicineDtoList.add(medicineAssignedDto);
			
		}
		
		
		return medicineDtoList;
		
		
	}
	

	public static List<MedicineAssignedDTO> createAllMedicineList(List<Medicine> medicines){
		List<MedicineAssignedDTO> medicineDtoList=new ArrayList<MedicineAssignedDTO>();
		for(Medicine medicine:medicines) {
			MedicineAssignedDTO medicineAssignedDto=new MedicineAssignedDTO();
			medicineAssignedDto.setMedicinePrice(medicine.getPrice());
			medicineAssignedDto.setMedicineName(medicine.getMedicineName());
			medicineAssignedDto.setMedicineId(medicine.getMedicineId());
			medicineDtoList.add(medicineAssignedDto);
		}
		return medicineDtoList;
	}
}
