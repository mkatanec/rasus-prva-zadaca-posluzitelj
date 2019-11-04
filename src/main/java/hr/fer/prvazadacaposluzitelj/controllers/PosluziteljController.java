package hr.fer.prvazadacaposluzitelj.controllers;

import hr.fer.prvazadacaposluzitelj.model.Measurement;
import hr.fer.prvazadacaposluzitelj.model.Sensor;
import hr.fer.prvazadacaposluzitelj.model.SensorDescription;
import hr.fer.prvazadacaposluzitelj.model.UserAddress;
import hr.fer.prvazadacaposluzitelj.services.HaversinService;
import hr.fer.prvazadacaposluzitelj.services.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PosluziteljController {

	@Autowired
	private SensorService sensorService;

	@Autowired
	private HaversinService haversinService;

	private static Logger logger = LoggerFactory.getLogger(PosluziteljController.class);

	@PostMapping("/register")
	public String register(@RequestBody SensorDescription sensorDescription) {
		logger.info(sensorDescription.toString() + " received");

		int addSensorResponse = sensorService.addSensor(sensorDescription);

		if (addSensorResponse == 0) {
			return "Added";
		} else if (addSensorResponse == 1) {
			return "Reconnected";
		} else {
			return "Already exists";
		}
	}

	@GetMapping("/sensor/{username}/searchNeighbor")
	public UserAddress searchNeighbor(@PathVariable("username") String username) {
		logger.info("searching for " + username + "s closest neighbor");

		final Sensor closestSensor = haversinService.getClosestSensor(username);

		if (closestSensor != null) {
			logger.info("closest to " + username + " is " + closestSensor.getUsername());

			UserAddress userAddress = new UserAddress();

			userAddress.setIpAddress(closestSensor.getIpAddress());
			userAddress.setPort(closestSensor.getPort());

			return userAddress;
		} else {
			logger.info("can't find closest sensor");

			return null;
		}
	}

	@GetMapping("/sensor/{username}")
	public Sensor sensor(@PathVariable("username") String username) {
		return sensorService.findByUsername(username);
	}

	@PostMapping("/storeMeasurements")
	public String storeMeasurements(@RequestBody Measurement measurement) {
		return Integer.toString(sensorService.storeMeasurements(measurement));
	}
}
