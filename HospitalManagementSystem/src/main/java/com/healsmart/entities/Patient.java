package com.healsmart.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {
    private Long Patient_id;
    
    private Doctor doctor;
    private User user;
    private Ward ward;
    private LocalDate dob;
    private LocalDate dateOfAddmission;
    private String bloodGroup;
    private String prescription;
    private int bedAllocated;
    private String paymentStatus;
    private String patientProblem;
    
    
    
    
}
