package hr.fer.prvazadacaposluzitelj.services;

import hr.fer.prvazadacaposluzitelj.controllers.PosluziteljController;
import hr.fer.prvazadacaposluzitelj.dao.SensorDoc;
import hr.fer.prvazadacaposluzitelj.model.Measurement;
import hr.fer.prvazadacaposluzitelj.model.Sensor;
import hr.fer.prvazadacaposluzitelj.model.SensorDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {
    @Autowired
    SensorDoc sensorDoc;

    private static Logger logger = LoggerFactory.getLogger(PosluziteljController.class);

    @Override
    public List<Sensor> findAll() {
        List<Sensor> sensors = new ArrayList<>();

        sensorDoc.findAll().forEach(sensors::add);

        return sensors;
    }

    @Override
    public Sensor findByUsername(String username) {
        return sensorDoc.findAllByUsername(username).get(0);
    }

    @Override
    public int addSensor(SensorDescription sensorDescription) {
        List<Sensor> allSensors = sensorDoc.findAllByUsername(sensorDescription.getUsername());
        if (allSensors.size() < 1) {
            Sensor sensor = new Sensor();

            sensor.setConnected(true);
            sensor.setUsername(sensorDescription.getUsername());
            sensor.setLatitude(sensorDescription.getLatitude());
            sensor.setLongitude(sensorDescription.getLongitude());
            sensor.setIpAddress(sensorDescription.getIpAddress());
            sensor.setPort(sensorDescription.getPort());

            sensorDoc.save(sensor);

            logger.info(sensorDescription.getUsername() + " added");

            return 0;
        } else if(!allSensors.get(0).getConnected()){
            logger.info(sensorDescription.getUsername() + " reconnecting");
            Sensor sensor = allSensors.get(0);

            sensor.setConnected(true);

            sensorDoc.save(sensor);

            return 1;
        } else {
            logger.info(sensorDescription.getUsername() + " is connected");

            return -1;
        }

    }

    @Override
    public int storeMeasurements(String username, Measurement measurement) {
        Sensor sensor = sensorDoc.findAllByUsername(username).get(0);

        sensorDoc.save(sensor);

        return 0;
    }
}
