package com.healsmart.dtos;

import java.util.List;

import com.healsmart.entities.DoctorVisit;
import com.healsmart.entities.MedicineAssigned;
import com.healsmart.entities.Patient;

import lombok.Data;

@Data
public class PatientChargesDTO {
	private double wardCharges;
	private double doctorCharges;
	private double medicineCharges;
	

	
	public static PatientChargesDTO calculateCharges(Patient patient,Long daysStayed) {
		PatientChargesDTO totalCharges=new PatientChargesDTO();
		double unitWardCharges=patient.getWard().getCharges();
		double wardCharges=unitWardCharges*daysStayed;
		
		
		double medicineCharges;
		double totalMedicineCharges=0;
		for(MedicineAssigned m:patient.getMedicines()) {
			totalMedicineCharges=totalMedicineCharges+ m.getMedicine().getPrice()*m.getMedicineQty();
		}
		totalCharges.setMedicineCharges(totalMedicineCharges);
		
		totalCharges.setWardCharges(wardCharges);
		List<DoctorVisit> visitList=patient.getVisits();
		double visitTotal=0;
		for(DoctorVisit d :visitList) {
			visitTotal =visitTotal+d.getVisits()*d.getDoctor().getCharges();
			
		}
		totalCharges.setDoctorCharges(visitTotal);
		
		return totalCharges;
		
	}

}
