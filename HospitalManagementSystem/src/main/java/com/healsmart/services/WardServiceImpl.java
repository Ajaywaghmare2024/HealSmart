package com.healsmart.services;

import static com.healsmart.dtos.WardDTO.createWard;
import static com.healsmart.dtos.WardDTO.createWardsList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healsmart.dtos.WardDTO;
import com.healsmart.entities.Ward;
import com.healsmart.repositories.WardRepository;

@Service
@Transactional
public class WardServiceImpl implements WardService {
	@Autowired
	private WardRepository wardRepository;

	@Override
	public int addWard(WardDTO ward) {
		return wardRepository.insertIntoWardTable(0, ward.getType(), 
				ward.getCharges(), 
				ward.getMaxCapacity(), 
				ward.getMaxCapacity());
		
		
	}

	@Override
	public List<WardDTO> getAllWards() {
		List<Ward> wards = wardRepository.findAll();
		List<WardDTO> wardDetails = createWardsList(wards);

		return wardDetails;
	}

	@Override
	public WardDTO getWardById(Long wardId) {
		Ward ward = wardRepository.getById(wardId);
		return createWard(ward);
	}

	@Override
	public String increaseBedCount(WardDTO wardData) {
		Ward ward = wardRepository.getById(wardData.getWardId());
		if (ward.getAvailability() < (ward.getMaxCapacity()) && (ward.getAvailability() > -1)) {
			ward.setAvailability(ward.getAvailability() + 1);
			Ward savedWard = wardRepository.save(ward);
			return "SUCCESS";

		} else {
			return "FAILURE";

		}
	}

	@Override
	public String decreaseBedCount(WardDTO wardData) {
		System.out.println("before success");
		// 41 //40max
		Ward ward = wardRepository.getById(wardData.getWardId());
		if ((ward.getAvailability() < (ward.getMaxCapacity() + 1)) && ward.getAvailability() > 0) {
			System.out.println("inside if success");
			ward.setAvailability(ward.getAvailability() - 1);
			Ward savedWard = wardRepository.save(ward);
			return "SUCCESS";

		} else {
			System.out.println("outside if success");
			return "FAILURE";

		}

	}

	@Override
	public int removeWard(Long wardId) {
		Ward byId = wardRepository.getById(wardId);
		if (byId.getPatients().isEmpty()) {
			System.out.println("inside ward delete");
			wardRepository.deleteById(wardId);
			return 1;
		}
		return 0;

	}

}
