package com.vehicle.example.vehicledemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Table(name = "Insurance")
@JsonInclude(Include.NON_EMPTY )
@JsonIgnoreProperties( value = {"plateNumber"}, allowSetters = true)
@Data
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "insurance_seq")
	@SequenceGenerator(name="insurance_seq",sequenceName="insurance_seq", allocationSize=1)
	private Long insuranceId;
	
	@ManyToOne
	@JoinColumn(name="plate_id", nullable=false)
	private PlateNumber plateNumber;
	
	private String name;
	
	private String code;
}
