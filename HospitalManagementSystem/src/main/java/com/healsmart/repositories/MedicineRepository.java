package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healsmart.entities.Medicine;
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	@Modifying
	@Query(value = "insert into medicines values (:medicineId, :medicineName, :price)",nativeQuery = true)
	Long insertIntoMedicineTable( @Param("medicineId")long medicineId,@Param("medicineName")String medicineName,@Param("price")double price);
	

}
