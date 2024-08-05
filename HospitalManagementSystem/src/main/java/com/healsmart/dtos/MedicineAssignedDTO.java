package com.healsmart.dtos;

import lombok.Data;

@Data
public class MedicineAssignedDTO {
    private Long patId;
    private Long medicineId;
    private String medicinePrescription;
    private int medicineQty;
    private String medicineName;
    private double medicinePrice;
    private Long medicineAssignedId;
}
