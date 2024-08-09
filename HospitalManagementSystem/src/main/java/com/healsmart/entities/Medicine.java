package com.healsmart.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medicines")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicineId;
	private String medicineName;
	private double price;

	@OneToMany(mappedBy = "medicine", cascade = CascadeType.PERSIST)
	private List<MedicineAssigned> mappedMedicines;
	

	//to set the medicine id in assigned medicine
	public void addAssignedMedicine(MedicineAssigned medicineAssigned) {
		medicineAssigned.setMedicine(this);
		mappedMedicines.add(medicineAssigned);
		
	}

}
