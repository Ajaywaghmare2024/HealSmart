package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healsmart.entities.MedicineAssigned;
@Repository
public interface MedicineAssignedRepository extends JpaRepository<MedicineAssigned, Long> {
	@Modifying
	@Query(value= "insert into medicines_assigned values(0, :patId,:medicineId,:prescription,:medicineQty)",nativeQuery = true)
	int addIntoMedicineAssigned(@Param("patId") long patId,@Param("medicineId") long medicineId,@Param("prescription") String prescription,@Param("medicineQty")int medicineQty);

}
