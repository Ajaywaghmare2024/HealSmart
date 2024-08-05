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
@Table(name="wards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long ward_id;
   private String type;
   private double charges;
   	private double avilability;
   	private double maxCapacity;
}
