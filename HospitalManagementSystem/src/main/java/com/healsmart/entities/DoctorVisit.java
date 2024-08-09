package com.healsmart.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode.Exclude;
@Entity
@Table(name="doctor_visits")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorVisit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long visitId;
	
	
	@Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="pat_id")
   private Patient patient;
	
	@Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="doctor_id")
	
   private Doctor doctor;
   private int visits;
   
}
