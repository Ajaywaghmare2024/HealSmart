package com.healsmart.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.healsmart.entities.Patient;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
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

	// **********patient extra details

	private Long patId;

	private LocalDate dob;

	private LocalDate dateOfAdmission;
	private Long wardId;
	private Long doctorId;
	private String bloodGroup;
	private String prescription;
	private int bedAllocated;
	private String paymentStatus;
	private String patientProblem;
	private String doctorFirstName;
	private String doctorLastName;
	private String type;
	private String doctorCellNo;
	// medicine assigned list

	public static List<PatientDTO> createPatient(List<Patient> employees) {
		List<PatientDTO> employeeDetails = new ArrayList<PatientDTO>();
		for (Patient p : employees) {
			PatientDTO createdPatient = new PatientDTO();
			createdPatient.setFirstName(p.getUser().getFirstName());
			createdPatient.setLastName(p.getUser().getLastName());
			createdPatient.setRole(p.getUser().getRole());
			createdPatient.setCellNo(p.getUser().getCellNo());
			createdPatient.setDob(p.getDob());
			createdPatient.setEmail(p.getUser().getEmail());
			createdPatient.setPatId(p.getPatientId());
			createdPatient.setUserId(p.getUser().getUserId());
			createdPatient.setWardId(p.getWard().getWardId());
			createdPatient.setDoctorId(p.getDoctor().getDoctorId());
			createdPatient.setDateOfAdmission(p.getDateOfAddmission());
			createdPatient.setBloodGroup(p.getBloodGroup());
			if (p.getPrescription() == null) {
				createdPatient.setPrescription("doctor will prescribe you");// to see if prescription is empty or not
			} else {
				createdPatient.setPrescription(p.getPrescription());
			}

			createdPatient.setBedAllocated(p.getBedAllocated());
			createdPatient.setPaymentStatus(p.getPaymentStatus());
			createdPatient.setPatientProblem(p.getPatientProblem());
			createdPatient.setDoctorFirstName(p.getDoctor().getEmployee().getUser().getFirstName());
			createdPatient.setDoctorLastName(p.getDoctor().getEmployee().getUser().getLastName());
			createdPatient.setPrescription(p.getPrescription());
			createdPatient.setType(p.getWard().getType());
			createdPatient.setDoctorCellNo(p.getUser().getCellNo());
			employeeDetails.add(createdPatient);
		}

		return employeeDetails;
	}

	// =======patient to send to front end===============

	public static PatientDTO getByIdPatient(Patient p) {
		PatientDTO createdPatient = new PatientDTO();
		createdPatient.setFirstName(p.getUser().getFirstName());
		createdPatient.setLastName(p.getUser().getLastName());
		createdPatient.setRole(p.getUser().getRole());
		createdPatient.setCellNo(p.getUser().getCellNo());
		createdPatient.setDob(p.getDob());
		createdPatient.setEmail(p.getUser().getEmail());
		createdPatient.setPatId(p.getPatientId());
		createdPatient.setUserId(p.getUser().getUserId());
		createdPatient.setWardId(p.getWard().getWardId());
		createdPatient.setDoctorId(p.getDoctor().getDoctorId());
		createdPatient.setDateOfAdmission(p.getDateOfAddmission());
		createdPatient.setBloodGroup(p.getBloodGroup());
		createdPatient.setPrescription(p.getPrescription());
		createdPatient.setBedAllocated(p.getBedAllocated());
		createdPatient.setPaymentStatus(p.getPaymentStatus());
		createdPatient.setPatientProblem(p.getPatientProblem());
		createdPatient.setDoctorFirstName(p.getDoctor().getEmployee().getUser().getFirstName());
		createdPatient.setDoctorLastName(p.getDoctor().getEmployee().getUser().getLastName());
		createdPatient.setPrescription(p.getPrescription());
		createdPatient.setType(p.getWard().getType());
		createdPatient.setDoctorCellNo(p.getDoctor().getEmployee().getUser().getCellNo());

		return createdPatient;

	}

	//**************patients of  doctor***************
	public static List<PatientDTO> createPatientsOfDoctor(List<Patient> employees, Long doctorId) {
		List<PatientDTO> employeeDetails = new ArrayList<PatientDTO>();
		for (Patient p : employees) {
			if (p.getDoctor().getDoctorId() == doctorId) {

				PatientDTO createdPatient = new PatientDTO();
				createdPatient.setFirstName(p.getUser().getFirstName());
				createdPatient.setLastName(p.getUser().getLastName());
				createdPatient.setRole(p.getUser().getRole());
				createdPatient.setCellNo(p.getUser().getCellNo());
				createdPatient.setDob(p.getDob());
				createdPatient.setEmail(p.getUser().getEmail());
				createdPatient.setPatId(p.getPatientId());
				createdPatient.setUserId(p.getUser().getUserId());
				createdPatient.setWardId(p.getWard().getWardId());
				createdPatient.setDoctorId(p.getDoctor().getDoctorId());
				createdPatient.setDateOfAdmission(p.getDateOfAddmission());
				createdPatient.setBloodGroup(p.getBloodGroup());
				if (p.getPrescription() == null) {
					createdPatient.setPrescription("doctor will prescribe you");// to see if prescription is empty or
																				// not
				} else {
					createdPatient.setPrescription(p.getPrescription());
				}

				createdPatient.setBedAllocated(p.getBedAllocated());
				createdPatient.setPaymentStatus(p.getPaymentStatus());
				createdPatient.setPatientProblem(p.getPatientProblem());
				createdPatient.setDoctorFirstName(p.getDoctor().getEmployee().getUser().getFirstName());
				createdPatient.setDoctorLastName(p.getDoctor().getEmployee().getUser().getLastName());
				createdPatient.setPrescription(p.getPrescription());
				createdPatient.setType(p.getWard().getType());
				createdPatient.setDoctorCellNo(p.getUser().getCellNo());
				employeeDetails.add(createdPatient);
			}

		}

		return employeeDetails;
	}
	
	

}
