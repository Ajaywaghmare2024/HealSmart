package com.healsmart.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healsmart.entities.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  
	@Modifying
	@Query(value="insert into employees values(:empId,:userId,:dob,:hireDate,:salary)",nativeQuery = true)
	Long insertIntoEmployeesTable(@Param("empId") long id,@Param("userId") long userId,@Param("dob") 
	LocalDate dob,@Param("hireDate") LocalDate hireDate,@Param("salary") double salary);
	
	@Query(value="select empId from employees where user_id=(select userId from users where email= :email)",nativeQuery = true)
	Long getEmpIdByEmail(@Param("email")String email);
}
