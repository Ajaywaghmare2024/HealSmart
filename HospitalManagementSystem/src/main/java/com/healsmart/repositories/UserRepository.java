package com.healsmart.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healsmart.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByEmail(String email);
	
	User findByEmailAndPassword(String email,String password);
	//updating employees details
	@Modifying
	@Query(value = "update users u "
			+ "inner join employees e on e.user_id =u.id "
			+ "set u.first_name = :firstName,u.last_name=:lastName ,e.dob=:dob,u.cell_no=:cellNo where u.id=:userId ",nativeQuery = true)
	Long updateFirstNameLastNameDobCellNo(@Param("firstName")String firstName,@Param("lastName") String lastName,@Param("dob") LocalDate dob,@Param("cellNo") String cellNo,@Param("userId") Long userId);
	
	
//delete employee using cell and userId
	@Modifying
	@Query(value = "delete from users u where u.id=:userId and cell_no=:cellNo",nativeQuery = true)
	Long deleteUsingCellNoAndUserId(@Param("userId") Long userId,@Param("cellNo") String cellNo);
	
	
	
	//inserting patient details in user
	@Modifying
	@Query(value = "insert into users values(:id,:firstName,:lastName,:email"
			+ ",:password,:cellNo,:role,:securityQuestion,:securityAnswer)",nativeQuery = true)
	long insertIntoUsers(@Param("id") long userId,@Param("firstName") String firstName,
			@Param("lastName") String lastName,@Param("email") String email,@Param("password") String password,@Param("cellNo") String cellNo,@Param("role") String role,@Param("securityQuestion") String securityQuestion,@Param("securityAnswer") String securityAnswer);

	@Modifying
	@Query(value = "update users set first_name= :firstName,last_name= :lastName,cell_no= :cellNo where id= :userId",nativeQuery = true)
	long updatePatientFirstNameLastNameCellNoWithUserId(@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("cellNo") String cellNo,@Param("userId") Long userId);
	//to implenent spring security
	User findByEmail(String email);
}

