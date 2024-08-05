package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healsmart.entities.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
