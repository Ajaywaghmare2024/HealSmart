package com.healsmart;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.healsmart.daos.IEmployeeDao;
import com.healsmart.daos.IPatientDao;
import com.healsmart.daos.IUserDao;
import com.healsmart.services.DoctorServices;

@SpringBootApplication()
public class HospitalManagementServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementServerApplication.class, args);
	}

	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IPatientDao patientDao;
	@Autowired
	DoctorServices dService;
	

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println("==========================================================================================================");
		System.out.println("==========================================================================================================");
		System.out.println("==========================================================================================================");
		System.out.println("==========================================================================================================");

		System.out.println("===========================Inside main function : welcome to HealSmart app====================================");
		System.out.println("==========================================================================================================");
		System.out.println("==========================================================================================================");
		System.out.println("==========================================================================================================");
		System.out.println("==========================================================================================================");

		

	}

}
