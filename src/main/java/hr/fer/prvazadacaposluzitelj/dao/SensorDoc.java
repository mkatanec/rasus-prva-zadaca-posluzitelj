package hr.fer.prvazadacaposluzitelj.dao;

import hr.fer.prvazadacaposluzitelj.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SensorDoc extends CrudRepository<Sensor, Integer> {
   List<Sensor> findAllByUsername(String username);
}
