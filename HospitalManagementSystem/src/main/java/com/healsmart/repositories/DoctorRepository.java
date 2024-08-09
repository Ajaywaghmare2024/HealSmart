package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healsmart.entities.Doctor;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	@Modifying
	@Query(value="insert into doctors values (:doctorId,:emp_id, :charges)",nativeQuery = true)
	int insertIntoDoctorTable(@Param("doctorId") long id,@Param("emp_id") long emp_id,@Param("charges")double charges);
	
	@Query(value = "select doctorId from doctors where emp_id="
			+ "(select empId from employees where user_id="
			+ "(select userId from users where first_name=:firstName and last_name=:lastName))",nativeQuery = true)
	int getDoctorIdByFirstNameAndLastName(@Param("firstName") String 	firstName,@Param("lastName") String lastName);
	
	
}
