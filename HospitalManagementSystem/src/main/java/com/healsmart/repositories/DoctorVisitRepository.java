package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healsmart.entities.DoctorVisit;
@Repository

public interface DoctorVisitRepository extends JpaRepository<DoctorVisit,Long> {
	@Modifying
	@Query(value = "insert into doctor_visits values (:visitId, :pat_id, :doctor_id, :visits)",nativeQuery = true)
	Long insertIntoDoctorVisitsTable( @Param("visitId")int i,@Param("pat_id")Long patId,@Param("doctor_id")Long doctorId,@Param("visits")int visits);
	//to increse visit count
	
	@Query(value="select * from doctor_visits where pat_id= :patId and doctor_id= :doctorId",nativeQuery = true)
	DoctorVisit getVisitsByPatIdAndDoctorId(@Param("patId")Long patId,@Param("doctorId")Long doctorId);

	
}
