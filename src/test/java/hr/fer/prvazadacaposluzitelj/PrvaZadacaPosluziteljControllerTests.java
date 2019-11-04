package hr.fer.prvazadacaposluzitelj;

import hr.fer.prvazadacaposluzitelj.controllers.PosluziteljController;
import hr.fer.prvazadacaposluzitelj.model.Sensor;
import hr.fer.prvazadacaposluzitelj.model.SensorDescription;
import hr.fer.prvazadacaposluzitelj.model.UserAddress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PrvaZadacaPosluziteljControllerTests {

    @Autowired
    private PosluziteljController posluziteljController;

    @Test
    public void contextLoads() {
        assertThat(posluziteljController).isNotNull();
    }

    @Before
    public void addSensors() {
        SensorDescription sensor1 = new SensorDescription();
        SensorDescription sensor2 = new SensorDescription();

        sensor1.setUsername("klijent1");
        sensor1.setLatitude(49);
        sensor1.setLongitude(48);
        sensor1.setIpAddress("210.201.001");
        sensor1.setPort(32);

        sensor2.setUsername("klijent2");
        sensor2.setLatitude(49);
        sensor2.setLongitude(49);
        sensor2.setIpAddress("210.201.002");
        sensor2.setPort(32);

    }

    @Test
    public void addSensor(){
        SensorDescription sensor1 = new SensorDescription();

        sensor1.setUsername("klijent3");
        sensor1.setLatitude(50);
        sensor1.setLongitude(50);
        sensor1.setIpAddress("210.201.001");
        sensor1.setPort(32);

    }

    @Test
    public void findClosestToKlijent1(){
//       UserAddress userAddress = posluziteljController.searchNeighbor("klijent1");

//       assertThat(userAddress).isNotNull();
    }

}
