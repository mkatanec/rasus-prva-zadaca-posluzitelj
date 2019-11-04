package hr.fer.prvazadacaposluzitelj.services;

import hr.fer.prvazadacaposluzitelj.model.Measurement;
import hr.fer.prvazadacaposluzitelj.model.Sensor;
import hr.fer.prvazadacaposluzitelj.model.SensorDescription;

import java.util.List;

public interface SensorService {
    List<Sensor> findAll();

    Sensor findByUsername(String username);

    int addSensor(SensorDescription sensorDescription);

    int storeMeasurements(Measurement measurement);
}
