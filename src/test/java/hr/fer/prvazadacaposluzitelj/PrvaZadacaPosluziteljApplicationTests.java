package hr.fer.prvazadacaposluzitelj;

import hr.fer.prvazadacaposluzitelj.model.SensorDescription;
import hr.fer.prvazadacaposluzitelj.model.UserAddress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrvaZadacaPosluziteljApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void shouldAddSensors() throws Exception {
        final String url = "http://localhost:" + port + "/register";
        URI uri = new URI(url);
        SensorDescription sensor1 = new SensorDescription();
        SensorDescription sensor2 = new SensorDescription();
        SensorDescription sensor3 = new SensorDescription();

        sensor1.setUsername("sensor1");
        sensor1.setLatitude(Math.random() * (15.87 - 16) + 15.87);
        sensor1.setLongitude(Math.random() * (45.75 - 45.85) + 45.75);
        sensor1.setIpAddress("210.201.001");
        sensor1.setPort(32);

        sensor2.setUsername("sensor2");
        sensor2.setLatitude(Math.random() * (15.87 - 16) + 15.87);
        sensor2.setLongitude(Math.random() * (45.75 - 45.85) + 45.75);
        sensor2.setIpAddress("210.201.002");
        sensor2.setPort(32);

        sensor3.setUsername("sensor3");
        sensor3.setLatitude(Math.random() * (15.87 - 16) + 15.87);
        sensor3.setLongitude(Math.random() * (45.75 - 45.85) + 45.75);
        sensor3.setIpAddress("210.201.003");
        sensor3.setPort(32);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SensorDescription> request1 = new HttpEntity<>(sensor1, headers);
        HttpEntity<SensorDescription> request2 = new HttpEntity<>(sensor2, headers);
        HttpEntity<SensorDescription> request3 = new HttpEntity<>(sensor3, headers);

        ResponseEntity<String> result1 = testRestTemplate.postForEntity(uri, request1, String.class);
        ResponseEntity<String> result2 = testRestTemplate.postForEntity(uri, request2, String.class);
        ResponseEntity<String> result3 = testRestTemplate.postForEntity(uri, request3, String.class);

        assertThat(result1.getStatusCodeValue()).isEqualTo(200);
        assertThat(result2.getStatusCodeValue()).isEqualTo(200);
        assertThat(result3.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void searchNeighborSensor1() throws Exception {
        final String url1 = "http://localhost:" + port + "/sensor/sensor1/searchNeighbor";
        final String url2 = "http://localhost:" + port + "/sensor/sensor2/searchNeighbor";
        final String url3 = "http://localhost:" + port + "/sensor/sensor3/searchNeighbor";
        URI uri1 = new URI(url1);
        URI uri2 = new URI(url2);
        URI uri3 = new URI(url3);

        ResponseEntity<UserAddress> result1 = testRestTemplate.getForEntity(uri1, UserAddress.class);
        ResponseEntity<UserAddress> result2 = testRestTemplate.getForEntity(uri2, UserAddress.class);
        ResponseEntity<UserAddress> result3 = testRestTemplate.getForEntity(uri3, UserAddress.class);

        assertThat(result1.getStatusCodeValue()).isEqualTo(200);
        assertThat(result2.getStatusCodeValue()).isEqualTo(200);
        assertThat(result3.getStatusCodeValue()).isEqualTo(200);
    }
}
