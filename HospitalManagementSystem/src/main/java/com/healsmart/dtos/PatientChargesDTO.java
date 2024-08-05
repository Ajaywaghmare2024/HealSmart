package com.healsmart.dtos;

import lombok.Data;

@Data
public class PatientChargesDTO {
	private double wardCharges;
	private double doctorCharges;
	private double medicineCharges;
}
