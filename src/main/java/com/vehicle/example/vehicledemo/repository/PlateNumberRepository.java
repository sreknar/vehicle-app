package com.vehicle.example.vehicledemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.example.vehicledemo.entity.PlateNumber;

@Repository
public interface PlateNumberRepository extends JpaRepository<PlateNumber, Long>{

}
