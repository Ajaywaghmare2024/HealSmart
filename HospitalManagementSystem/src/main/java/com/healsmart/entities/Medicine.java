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
@Table(name="medicines")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
  private Long medicine_id;
  private String medicine_name;
  private double price;
  
}
