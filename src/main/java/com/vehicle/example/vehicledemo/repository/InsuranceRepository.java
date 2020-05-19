package com.vehicle.example.vehicledemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vehicle.example.vehicledemo.entity.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long>{

	@Query("SELECT st FROM Insurance st WHERE st.plateNumber.plateId = ?1")
	Insurance findByPlateId(Long plateId);

}
