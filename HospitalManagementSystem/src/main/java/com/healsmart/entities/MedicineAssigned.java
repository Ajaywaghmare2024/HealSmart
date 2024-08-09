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

@Entity
@Table(name="medicine_assigned")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MedicineAssigned {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineAssignId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="patient_id")
    private Patient patient;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="medicine_id")
    private  Medicine medicine;
    private String prescription;
    private int medicineQty;
    
    public MedicineAssigned (String prescription,int medicineQty) {
		super();
		this.prescription=prescription;
		this.medicineQty=medicineQty;
	}
    
}
