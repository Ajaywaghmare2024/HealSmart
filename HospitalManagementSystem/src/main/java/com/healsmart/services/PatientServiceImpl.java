package com.healsmart.services;

import static com.healsmart.dtos.MedicineAssignedDTO.createMedicineListForPatient;
import static com.healsmart.dtos.PatientDTO.createPatient;
import static com.healsmart.dtos.PatientDTO.createPatientsOfDoctor;
import static com.healsmart.dtos.PatientDTO.getByIdPatient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healsmart.custom_exception.NoSuchPatientFoundException;
import com.healsmart.custom_exception.PatientAlreadyExistsException;
import com.healsmart.dtos.MedicineAssignedDTO;
import com.healsmart.dtos.PatientChargesDTO;
import com.healsmart.dtos.PatientDTO;
import com.healsmart.entities.Doctor;
import com.healsmart.entities.DoctorVisit;
import com.healsmart.entities.MedicineAssigned;
import com.healsmart.entities.Patient;
import com.healsmart.entities.User;
import com.healsmart.entities.Ward;
import com.healsmart.repositories.DoctorRepository;
import com.healsmart.repositories.DoctorVisitRepository;
import com.healsmart.repositories.EmployeeRepository;
import com.healsmart.repositories.PatientRepository;
import com.healsmart.repositories.UserRepository;
import com.healsmart.repositories.WardRepository;

@Service
@Transactional
public class PatientServiceImpl implements PatientService{
	
	@Autowired
     PatientRepository patientRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private DoctorVisitRepository doctorVisitRepository;
	@Autowired
	private WardRepository wardRepository;

	@Override
	public Long addPatient(PatientDTO patientDTO) {
		if(!userRepository.existsByEmail(patientDTO.getEmail())) {
			userRepository.insertIntoUsers(0, patientDTO.getFirstName(), patientDTO.getLastName(), patientDTO.getEmail(), patientDTO.getPassword(), patientDTO.getCellNo(), patientDTO.getRole(), patientDTO.getSecurityQuestion(), patientDTO.getSecurityAnswer());
			User user=userRepository.findByEmail(patientDTO.getEmail());
			Long updateCount=patientRepository.insertIntoPatients(0, user.getUserId(),patientDTO.getDoctorId() , patientDTO.getWardId(), patientDTO.getDateOfAdmission(), patientDTO.getBloodGroup(), patientDTO.getDob(), patientDTO.getPrescription(), patientDTO.getBedAllocated(), patientDTO.getPaymentStatus(), patientDTO.getPatientProblem());
			Patient patient=patientRepository.findByUserId(user.getUserId());
			doctorVisitRepository.insertIntoDoctorVisitsTable(0, patient.getPatientId(), patientDTO.getDoctorId(),0);
			return updateCount;
		}else {
			throw new PatientAlreadyExistsException("patient with email = "+patientDTO.getEmail()+" exists!!!");
			
		}
	}

	@Override
	public List<PatientDTO> getAllPatients() {
		
		List<Patient> patients=patientRepository.findAll();
		List<PatientDTO> patientList=createPatient(patients);
		return patientList;
	}

	@Override
	public PatientDTO getPatientById(Long patId) throws NoSuchPatientFoundException{
		if(patientRepository.existsById(patId)) {
			Patient patient=patientRepository.getById(patId);
			PatientDTO patientDetailsToSend=getByIdPatient(patient);
			return patientDetailsToSend;
		}else {
			throw new NoSuchPatientFoundException("patient with id = "+patId+" does not exists!!!");
		}
		
	}

	@Override
	public List<MedicineAssignedDTO> getMedicineByPatId(Long patId) {
		Patient patient=patientRepository.getById(patId);
		List<MedicineAssigned> medicines=patient.getMedicines();
		List<MedicineAssignedDTO> medicineToSend=createMedicineListForPatient(medicines);
		return medicineToSend;
	}

	@Override
	public void updatePatientDetails(PatientDTO patientDTO) {
		if(patientRepository.existsById(patientDTO.getPatId())) {
			Patient updatePatient = patientRepository.getById(patientDTO.getPatId());
			//to add to visit table
			
				DoctorVisit initVisit=doctorVisitRepository.getVisitsByPatIdAndDoctorId(patientDTO.getPatId(), patientDTO.getDoctorId());
				System.out.println("------------------>initvisit"+initVisit);
				if(initVisit==null) {
					doctorVisitRepository.insertIntoDoctorVisitsTable(0, patientDTO.getPatId(), patientDTO.getDoctorId(), 0);
				}
				//got corrusponding ward by ward Id
			Ward updateWard=wardRepository.getById(patientDTO.getWardId());
			
			//added patient in ward list
			updateWard.addPatient(updatePatient);
			//new ward set in patient
			updatePatient.setWard(updateWard);
			//========================================
			//got new doctor by id 
			Doctor updatedDoctor=doctorRepository.getById(patientDTO.getDoctorId());
			
			
			
			updatedDoctor.addPatient(updatePatient);//patient added in doctor list 
			updatePatient.setDoctor(updatedDoctor);//doctor added to patient list
			
			updatePatient.getUser().setFirstName(patientDTO.getFirstName());
			updatePatient.getUser().setLastName(patientDTO.getLastName());
			updatePatient.getUser().setCellNo(patientDTO.getCellNo());
			
			updatePatient.setDob(patientDTO.getDob());
			updatePatient.setBedAllocated(patientDTO.getBedAllocated());
			updatePatient.setBloodGroup(patientDTO.getBloodGroup());
			//=======================================
			//update visits
			Patient savedPatient = patientRepository.save(updatePatient);
			
			
		}else {
			throw new NoSuchPatientFoundException("patient with id = "+patientDTO.getPatId()+" does not exists!!!");
		}
		
	
		
	}

	@Override
	public int removePatientById(Long patId) {
		if(patientRepository.existsById(patId)) {
			patientRepository.deleteById(patId);
			return 1;
		}else {
			throw new NoSuchPatientFoundException("patient with id = "+patId+" does not exists!!!");
		}
		
	}

	@Override
	public PatientChargesDTO calculateChargesByPatId(Long patId) {
		PatientChargesDTO patientCharges=new PatientChargesDTO();
		Long daysStayed = patientRepository.calculateDaysOfStayOfPatient(patId);
		Patient patient=patientRepository.getById(patId);
		patientCharges=calculateChargesByPatId(daysStayed);
		return patientCharges;
	}

	@Override
	public void updatePaymentStatusByPatId(PatientDTO patientData) {
		
		if(patientRepository.existsById(patientData.getPatId())) {
			Patient patient=patientRepository.getById(patientData.getPatId());
			patient.setPaymentStatus(patientData.getPaymentStatus());
			patientRepository.save(patient);
		}else {
			throw new NoSuchPatientFoundException("patient with id = "+patientData.getPatId()+" does not exists!!!");
		}
	}

	@Override
	public Boolean checkIfBedAvailable(PatientDTO bedData) {
		return patientRepository.existsByBedAllocatedAndWard_WardId
				(bedData.getBedAllocated(),bedData.getWardId()); 
	}

	@Override
	public List<PatientDTO> getPatientsOfDoctor(Long PatientId) {
		List<Patient> patients=patientRepository.findAll();
		List<PatientDTO> patientList=createPatientsOfDoctor(patients,PatientId);
		return patientList;
	}

}
