package com.healsmart.services;

import com.healsmart.dtos.DoctorVisitDTO;

public interface DoctorVisitService {
   public Long addVisit(DoctorVisitDTO visitDTO);
   
   public void increaseVisitCount(DoctorVisitDTO visitDTO);
}
