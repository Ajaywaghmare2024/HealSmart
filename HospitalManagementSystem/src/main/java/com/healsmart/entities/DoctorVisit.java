package com.healsmart.entities;

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
@Table(name="doctor_visits")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DoctorVisit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long Doctor_Visit_id;
   private Patient patient;
   private Doctor doctor;
   private int visit;
   
}
