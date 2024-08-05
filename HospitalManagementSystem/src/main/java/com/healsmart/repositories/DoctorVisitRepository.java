package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healsmart.entities.DoctorVisit;

public interface DoctorVisitRepository extends JpaRepository<DoctorVisit,Long> {
	@Modifying
	@Query(value = "insert into doctor_visits values (:id, :pat_id, :doctor_id, :visits)",nativeQuery = true)
	Long insertIntoDoctorVisitsTable( @Param("id")int i,@Param("pat_id")Long patId,@Param("doctor_id")Long doctorId,@Param("visits")int visits);
	//to increse visit count
	
	@Query(value="select * from doctor_visits where pat_id= :patId and doctor_id= :doctorId",nativeQuery = true)
	DoctorVisit getVisitsByPatIdAndDoctorId(@Param("patId")Long patId,@Param("doctorId")Long doctorId);

	
}
