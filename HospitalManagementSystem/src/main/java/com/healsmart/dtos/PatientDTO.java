package com.healsmart.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class PatientDTO {
	 private Long userId;
		private String firstName;
		private String lastName;
		private String email;
		@JsonProperty(access = Access.WRITE_ONLY)
		private String password;
		private String role;
		private String cellNo;
		private String securityQuestion;
		private String securityAnswer;
		
		//**********patient extra details
		
		private Long patId;
		
		private LocalDate dob;
		
		private LocalDate dateOfAdmission;
		private Long wardId;
		private Long doctorId;
		private String bloodGroup;
		private String prescription;
		private int bedAlloted;
		private String paymentStatus;
		private String patientProblem;
		private String doctorFirstName;
		private String doctorLastName;
		private String type;
		private String doctorCellNo;
		//medicine assigned list
		
}
