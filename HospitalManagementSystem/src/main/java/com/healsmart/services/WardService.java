package com.healsmart.services;

import java.util.List;

import com.healsmart.dtos.WardDTO;

public interface WardService {
	public int addWard(WardDTO ward);
	
	public List<WardDTO> getAllWards();
	
	public WardDTO getWardById(Long wardId);
	
	public String increaseBedCount(WardDTO wardData);
	
	public String decreaseBedCount(WardDTO wardData);
	
    public int removeWard(Long wardId);
}
