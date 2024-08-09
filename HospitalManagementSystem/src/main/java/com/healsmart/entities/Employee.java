package com.healsmart.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Table(name = "employees")
@Getter
@Setter

@AllArgsConstructor

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	private LocalDate dob;
	private LocalDate hireDate;

	@Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	private double salary;

	@Exclude
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	private Doctor doctor;

	public void addDoctor(Doctor d) {
		doctor = d;
		doctor.setEmployee(this);
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(LocalDate dob, LocalDate hireDate, User user, double salary) {
		super();
		this.dob = dob;
		this.hireDate = hireDate;
		this.user = user;
		this.user.setEmployee(this);
		this.salary = salary;
	}

	public Employee(LocalDate dob, LocalDate hireDate, double salary) {
		super();
		this.dob = dob;
		this.hireDate = hireDate;
		this.salary = salary;
	}

}
