package com.healsmart.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healsmart.entities.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
		@Modifying
		@Query(value = "insert into patients values (:patientId, :user_id, :ward_id, :doctor_id, :date_of_admission"
				+ ", :blood_group, :dob, :prescription, :bed_alloted, :payment_status, :patient_problem)",nativeQuery = true)
		
		Long insertIntoPatients(@Param("patientId") long patientId ,@Param("user_id") Long user_id ,@Param("doctor_id") Long doctor_id,@Param("ward_id") long ward_id
				,@Param("date_of_admission") LocalDate date_of_admission,@Param("blood_group") String blood_group ,@Param("dob") LocalDate dob ,
				@Param("prescription") String prescription ,@Param("bed_alloted") int bed_alloted,@Param("payment_status") String payment_status ,@Param("patient_problem") String patient_problem);
		
		//update patients dob bedalloted blood group using patId
		
		@Modifying
		@Query(value = "update patients set prescription= :prescription  where patientId= :patId",nativeQuery = true)
		Long updatePatientPrescription(@Param("prescription") String prescription,@Param("patId") long patId);
	
		
		//querry to calculate difference between the date of admission and today
//		select datediff(date_of_admission,date(now())) from patients where id=37;
		
		@Query(value="select datediff(date(now()),date_of_admission) from patients where patientId = :patId",nativeQuery = true)
		Long calculateDaysOfStayOfPatient(@Param("patId") Long patId);
		
		//to check if bed is not alloted 
		 @Query("SELECT COUNT(p) > 0 FROM Patient p WHERE p.bedAllocated = :bedAllocated AND p.ward.wardId = :wardId")
		    Boolean existsByBedAllocatedAndWard_WardId(@Param("bedAllocated") int bedAllocated, @Param("wardId") Long wardId);

	    @Query("SELECT p FROM Patient p WHERE p.user.userId = :userId")
	    Patient findByUserId(@Param("userId") Long userId);


	
		
		
		//to update visits
		
		
		
	
}
