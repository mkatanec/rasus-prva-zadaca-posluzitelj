package hr.fer.prvazadacaposluzitelj.controllers;

import hr.fer.prvazadacaposluzitelj.model.Measurement;
import hr.fer.prvazadacaposluzitelj.model.SensorDescription;
import hr.fer.prvazadacaposluzitelj.model.UserAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PosluziteljController {

    private static Logger logger = LoggerFactory.getLogger(PosluziteljController.class);

    private List<SensorDescription> sensors = new ArrayList<>();

    @GetMapping("/register")
    public String register(@RequestBody SensorDescription sensorDescription) {
        logger.info(sensorDescription.toString());

        sensors.forEach(sensor -> {
            if (sensor.getUsername().equals(sensorDescription.getIpAddress())) {
                return "exists";
            }
        });

        sensors.add(sensorDescription);

        return "ola";
    }

    @GetMapping("/searchNeighbor")
    public UserAddress searchNeighbor(@RequestBody String userName) {
        return null;
    }

    @PostMapping("/storeMeasurements")
    public void storeMeasurements(@RequestBody Measurement measurement) {

    }
}
