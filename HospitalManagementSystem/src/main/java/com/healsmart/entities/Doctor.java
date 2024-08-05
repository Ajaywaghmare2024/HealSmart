package com.healsmart.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
   private Long Doctor_id;
   private double charges;
   @JoinColumn(name="emp_id")
   private Employee employee;
   
   
   
   
}
