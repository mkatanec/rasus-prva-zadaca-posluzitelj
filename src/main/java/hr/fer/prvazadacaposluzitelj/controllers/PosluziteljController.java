package hr.fer.prvazadacaposluzitelj.controllers;

import hr.fer.prvazadacaposluzitelj.dao.SensorDoc;
import hr.fer.prvazadacaposluzitelj.model.Measurement;
import hr.fer.prvazadacaposluzitelj.model.Sensor;
import hr.fer.prvazadacaposluzitelj.model.SensorDescription;
import hr.fer.prvazadacaposluzitelj.model.UserAddress;
import hr.fer.prvazadacaposluzitelj.services.HaversinService;
import hr.fer.prvazadacaposluzitelj.services.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PosluziteljController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private HaversinService haversinService;

    private static Logger logger = LoggerFactory.getLogger(PosluziteljController.class);

    @PostMapping("/register")
    public void register(@RequestBody SensorDescription sensorDescription) {
        logger.info(sensorDescription.toString() + " received");

        if (sensorService.addSensor(sensorDescription) == 0) {
        } else {
        }
    }

    @GetMapping("/searchNeighbor/{username}")
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

    @PostMapping("/storeMeasurements/{username}")
    public void storeMeasurements(@PathVariable String username, @RequestBody Measurement measurement) {
        sensorService.storeMeasurements(username, measurement);
    }
}
