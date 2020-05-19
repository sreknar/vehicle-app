package com.vehicle.example.vehicledemo.dto;

import com.vehicle.example.vehicledemo.entity.Insurance;
import com.vehicle.example.vehicledemo.entity.Vehicle;

import lombok.Data;

@Data
public class RegistrationDto {

	private String plateNumber;
	
	private VehicleInfoDto registration;
	
	private Insurance Insurer;
	
	private Vehicle vehicle;	
	
}
