package com.healsmart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healsmart.dtos.DoctorVisitDTO;
import com.healsmart.entities.DoctorVisit;
import com.healsmart.repositories.DoctorVisitRepository;
import com.healsmart.repositories.EmployeeRepository;
import com.healsmart.repositories.UserRepository;
import com.healsmart.repositories.WardRepository;
@Service
@Transactional
public class DoctorVisitServiceImpl  implements DoctorVisitService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Autowired
	private DoctorVisitRepository doctorVisitRepository;

	@Override
	public Long addVisit(DoctorVisitDTO visitDTO) {
		
		return doctorVisitRepository.insertIntoDoctorVisitsTable
				(0, visitDTO.getPatientId(),visitDTO.getDoctorId(), 0);
	}

	@Override
	public void increaseVisitCount(DoctorVisitDTO visitDTO) {

		DoctorVisit visit=doctorVisitRepository.getVisitsByPatIdAndDoctorId
				(visitDTO.getPatientId(),visitDTO.getDoctorId());
		visit.setVisits(visit.getVisits()+1);
		doctorVisitRepository.save(visit);

		
	}

}
