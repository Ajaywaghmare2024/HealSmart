package com.healsmart.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	private int bedAllocated;
	
	
  ///----------------connect to user------------//
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	///----------------connect to Doctor------------//
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	///----------------connect to Ward------------//
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="ward_id")
	private Ward ward;
	
	// -------connect to doctor visit table-----
	@OneToMany(mappedBy = "patient" , cascade  = CascadeType.ALL)
	private List<DoctorVisit> visits;
	
	private LocalDate dob;
	private LocalDate dateOfAddmission;
	private String bloodGroup;
	private String prescription;
	
	private String paymentStatus;
	private String patientProblem;
	
	// one patient have many medicine assigned 
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
	List<MedicineAssigned>medicines;

	
	// adding patient to doctorVisit table
	public void addPatient(DoctorVisit visit) {
		visit.setPatient(this);
		visits.add(visit);
	}
	
	//*********************testing: add all to add into db
		public Patient(User user, Ward ward, Doctor doctor) {
			super();
			this.user = user;
			this.ward = ward;
			this.doctor = doctor;
		}
		public Patient(Long patientId, LocalDate dateOfAddmission, LocalDate dob, String bloodGroup, String prescription, int bedAllocated,
				String paymentStatus, String patientProblem) {
			super();
			this.patientId = patientId;
			this.dateOfAddmission = dateOfAddmission;
			this.dob = dob;
			this.bloodGroup = bloodGroup;
			this.prescription = prescription;
			this.bedAllocated = bedAllocated;
			this.paymentStatus = paymentStatus;
			this.patientProblem = patientProblem;
		}
		
		//constructor created for testing purpose
		public Patient(Long patientId, String prescription, int bedAllocated) {
			super();
			this.patientId = patientId;
			this.prescription = prescription;
			this.bedAllocated = bedAllocated;
		}
		
		
		//add medicine to the list ie store patId in medicine table
		public void medicineAssigned(MedicineAssigned medicine) {
			medicine.setPatient(this);
			medicines.add(medicine);
		}
		
}
