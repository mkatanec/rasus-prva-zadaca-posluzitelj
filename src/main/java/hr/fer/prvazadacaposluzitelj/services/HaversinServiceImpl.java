package hr.fer.prvazadacaposluzitelj.services;

import hr.fer.prvazadacaposluzitelj.dao.SensorDoc;
import hr.fer.prvazadacaposluzitelj.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HaversinServiceImpl implements HaversinService {

    @Autowired
    private SensorDoc sensorDoc;

    @Override
    public Sensor getClosestSensor(String sensorUsername) {
        Map<String, Double> distanecs = new HashMap<>();

        final Sensor sensorFrom = sensorDoc.findAllByUsername(sensorUsername).get(0);

        sensorDoc.findAll().forEach(sensor -> {
            if (!sensor.getUsername().equals(sensorUsername)) {
                distanecs.put(sensor.getUsername(), calculateDistance(sensor, sensorFrom));
            }
        });

        Map.Entry<String, Double> minDistance = null;

        for(Map.Entry<String, Double> distance : distanecs.entrySet()){
            if(minDistance == null || distance.getValue() < minDistance.getValue()){
                minDistance = distance;
            }
        }

        return sensorDoc.findAllByUsername(minDistance.getKey()).get(0);
    }

    public Double calculateDistance(Sensor sensor1, Sensor sensor2) {
        final int R = 6371;

        double dlon = sensor2.getLongitude() - sensor1.getLongitude();
        double dlat = sensor2.getLatitude() - sensor1.getLatitude();
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(sensor1.getLatitude()) * Math.cos(sensor2.getLatitude()) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
