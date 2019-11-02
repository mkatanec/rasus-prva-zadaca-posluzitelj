package hr.fer.prvazadacaposluzitelj.services;

import hr.fer.prvazadacaposluzitelj.model.Sensor;
import org.springframework.stereotype.Service;

public interface HaversinService {
    Sensor getClosestSensor(String sensorUsername);
}
