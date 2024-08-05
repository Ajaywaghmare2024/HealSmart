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
@Table(name="medicineAssigned")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MedicineAssigned {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineAssignId;
    private Patient patient;
    private  Medicine medicine;
    private String prescription;
    private int medicineQty;
    
}
