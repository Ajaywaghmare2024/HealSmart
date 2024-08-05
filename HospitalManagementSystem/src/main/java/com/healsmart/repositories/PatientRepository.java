package com.healsmart.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healsmart.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	//under scores in name are used for easyness only no other intention
		@Modifying
		@Query(value = "insert into patients values (:id, :user_id, :ward_id, :doctor_id, :date_of_admission"
				+ ", :blood_group, :dob, :prescription, :bed_alloted, :payment_status, :patient_problem)",nativeQuery = true)
		Long insertIntoPatients(@Param("id") Long id ,@Param("user_id") Long user_id ,@Param("doctor_id") Long doctor_id,@Param("ward_id") int ward_id
				,@Param("date_of_admission") Date date_of_admission,@Param("blood_group") String blood_group ,@Param("dob") Date dob ,
				@Param("prescription") String prescription ,@Param("bed_alloted") int bed_alloted,@Param("payment_status") String payment_status ,@Param("patient_problem") String patient_problem);
		//update patients dob bedalloted blood group using patId
		
		@Modifying
		@Query(value = "update patients set prescription= :prescription  where id= :patId",nativeQuery = true)
		Long updatePatientPrescription(@Param("prescription") String prescription,@Param("patId") Long long1);
	//querry to calculate difference between the date of admission and today
//		select datediff(date_of_admission,date(now())) from patients where id=37;
		@Query(value="select datediff(date(now()),date_of_admission) from patients where id = :patId",nativeQuery = true)
		Long calculateDaysOfStayOfPatient(@Param("patId") Long patId);
		
		//to check if bed is not alloted 
		Boolean existsByBedAllotedAndWardId(Long bedNo,Long wardId);
		//to update visits
		//Patient findByUserId(Long userId);

}
