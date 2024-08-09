package com.healsmart.dtos;

import java.util.ArrayList;
import java.util.List;

import com.healsmart.entities.Ward;

import lombok.Data;

@Data
public class WardDTO {
	private Long wardId;
	private String type;
	private double charges;
	private double availability;
	private double maxCapacity;
	

	public static List<WardDTO> createWardsList(List<Ward> wards){
		List<WardDTO> wardsDtoList=new ArrayList<>();
		
		
		for(Ward w:wards) {
			WardDTO wardDto=new WardDTO();
			wardDto.setWardId(w.getWardId());
			wardDto.setType(w.getType());
			wardDto.setCharges(w.getCharges());
			wardDto.setAvailability(w.getAvailability());
			wardDto.setMaxCapacity(w.getMaxCapacity());
			wardsDtoList.add(wardDto);
		}
		
		return wardsDtoList;
	}
	
	public static WardDTO createWard(Ward ward) {
		WardDTO wardToSend=new WardDTO();
		wardToSend.setAvailability(ward.getAvailability());
		wardToSend.setCharges(ward.getCharges());
		wardToSend.setMaxCapacity(ward.getMaxCapacity());
		wardToSend.setType(ward.getType());
		wardToSend.setWardId(ward.getWardId());
		return wardToSend;
	}
}
