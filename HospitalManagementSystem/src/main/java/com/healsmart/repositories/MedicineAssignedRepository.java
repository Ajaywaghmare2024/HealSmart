package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healsmart.entities.MedicineAssigned;

public interface MedicineAssignedRepository extends JpaRepository<MedicineAssigned, Long> {

}
