package com.healsmart.dtos;

import lombok.Data;

@Data
public class DoctorVisitDTO {
	private Long visitId;
	private Long patientId;
	private Long doctorId;
	private int visit;
}
