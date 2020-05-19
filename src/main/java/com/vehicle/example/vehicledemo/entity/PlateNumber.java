package com.vehicle.example.vehicledemo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Table(name = "PlateNumber")
@JsonInclude(Include.NON_EMPTY )
@Data
public class PlateNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "plate_num_seq")
	@SequenceGenerator(name="plate_num_seq",sequenceName="plate_num_seq", allocationSize=1)
	private Long plateId;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id", nullable=false)
	private Vehicle vehicle;
	
	private String plateNumber;
	
	private Boolean isExpired;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDateTime;
}
