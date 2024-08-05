package com.healsmart.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@ToString
@Table(name="employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Emp_id;
    private LocalDate dob;
    private LocalDate HireDate;
    private User user;
    private double salary;
    private Doctor doctor;
    
}
