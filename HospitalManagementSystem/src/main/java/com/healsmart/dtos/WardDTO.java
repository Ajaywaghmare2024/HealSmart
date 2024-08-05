package com.healsmart.dtos;

import lombok.Data;

@Data
public class WardDTO {
	private Long wardId;
	private String type;
	private double charges;
	private double availability;
	private double maxCapacity;
}
