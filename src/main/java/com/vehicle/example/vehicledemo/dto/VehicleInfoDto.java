package com.vehicle.example.vehicledemo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleInfoDto {

	private Boolean isExpired;

	private Date expiryDate;
}
