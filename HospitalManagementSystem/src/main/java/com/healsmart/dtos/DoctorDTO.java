package com.healsmart.dtos;

import java.util.ArrayList;
import java.util.List;

import com.healsmart.entities.Doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
	private Long doctorId;
	private String firstName;
	private String lastName;

	public static List<DoctorDTO> createDoctorsList(List<Doctor> doctors) {
		List<DoctorDTO> createDoctorDtoList = new ArrayList<>();

		for (Doctor doctor : doctors) {
			DoctorDTO createDoctorDto = new DoctorDTO();

			createDoctorDto.setFirstName(doctor.getEmployee().getUser().getFirstName());
			createDoctorDto.setLastName(doctor.getEmployee().getUser().getLastName());
			createDoctorDto.setDoctorId(doctor.getDoctorId());
			createDoctorDtoList.add(createDoctorDto);
//			

		}

		return createDoctorDtoList;

	}

}
