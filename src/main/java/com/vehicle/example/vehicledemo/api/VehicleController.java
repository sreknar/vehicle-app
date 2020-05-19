package com.vehicle.example.vehicledemo.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.example.vehicledemo.dto.RegistrationDto;
import com.vehicle.example.vehicledemo.dto.VehicleInfoDto;
import com.vehicle.example.vehicledemo.entity.Insurance;
import com.vehicle.example.vehicledemo.entity.PlateNumber;
import com.vehicle.example.vehicledemo.entity.Vehicle;
import com.vehicle.example.vehicledemo.repository.InsuranceRepository;
import com.vehicle.example.vehicledemo.repository.PlateNumberRepository;
import com.vehicle.example.vehicledemo.repository.VehicleRepository;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = { "VehicleController" }, description = "REST API for vehicle details")
public class VehicleController {

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Autowired
	private PlateNumberRepository plateNumberRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/api/test")
	public ResponseEntity<String> greeting() {
		return ResponseEntity.ok().body("Hello, World");
	}

	@PostMapping("/api/vehicle")
	public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
		Vehicle sVehicle = vehicleRepository.save(vehicle);
		return ResponseEntity.ok().body(sVehicle);
	}

	@PostMapping("/api/plate-number/{vehicleId}")
	public ResponseEntity<PlateNumber> createPlateNumber(@PathVariable("vehicleId") Long vehicleId,
			@Valid @RequestBody PlateNumber plateNumber) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleId(vehicleId);
		plateNumber.setVehicle(vehicle);
		PlateNumber svdPlateNumber = plateNumberRepository.save(plateNumber);
		return ResponseEntity.ok().body(svdPlateNumber);
	}

	@PostMapping("/api/insurance/{plateId}")
	public ResponseEntity<Insurance> createInsurance(@PathVariable("plateId") Long plateId,
			@Valid @RequestBody Insurance insurance) {
		PlateNumber plateNumber = new PlateNumber();
		plateNumber.setPlateId(plateId);
		insurance.setPlateNumber(plateNumber);
		Insurance svdInsurance = insuranceRepository.save(insurance);
		return ResponseEntity.ok().body(svdInsurance);
	}

	@GetMapping("/api/all-registration-info")
	public ResponseEntity<List<RegistrationDto>> getAllRegistations() {
		List<RegistrationDto> regList = getRegistrationDetails();
		return ResponseEntity.ok().body(regList);
	}

	private List<RegistrationDto> getRegistrationDetails() {

		List<PlateNumber> plates = plateNumberRepository.findAll();

		List<RegistrationDto> regList = new ArrayList<RegistrationDto>();
		plates.forEach(plate -> {
			RegistrationDto regis = new RegistrationDto();
			try {
				Insurance insurance = insuranceRepository.findByPlateId(plate.getPlateId());

				regis.setInsurer(insurance);
				regis.setRegistration(new VehicleInfoDto(plate.getIsExpired(), plate.getExpiryDate()));
				regis.setVehicle(plate.getVehicle());
				regis.setPlateNumber(plate.getPlateNumber());
				regList.add(regis);

			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		return regList;
	}

}
