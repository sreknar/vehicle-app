package com.vehicle.example.vehicledemo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Table(name = "Vehicle")
@JsonInclude(Include.NON_EMPTY )
@Data
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "vehicle_seq")
	@SequenceGenerator(name="vehicle_seq",sequenceName="vehicle_seq", allocationSize=1)
	Long vehicleId;
	
	String type;
	
	String make;
	
	String model;
	
	String colour;
	
	Long vin;
	
	Integer tareWeight;
	
	String grossMass;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	Date creationDateTime;
}
