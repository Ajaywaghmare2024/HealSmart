package com.healsmart.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long doctorId;

	@Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id")
	private Employee employee;

	private double charges;

	@Exclude
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
	private List<Patient> patients;

	@Exclude
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
	private List<DoctorVisit> visits;

	public Doctor() {
		patients = new ArrayList<Patient>();
	}

	
	// to set the doctor for patient
	public void addPatient(Patient patient) {
		patient.setDoctor(this);
		this.patients.add(patient);
	}

	// adding visit to doctor visit
	
	public void addVisit(DoctorVisit visit) {
		visit.setDoctor(this);
		this.visits.add(visit);
	}

	///// ***********************connecting foreign key
	public void addEmployee(Employee e) {
		this.employee = e;
		this.employee.setDoctor(this);

	}

	public Doctor(Long doctorId, double charges) {
		super();
		this.doctorId = doctorId;
		this.charges = charges;
	}

}
