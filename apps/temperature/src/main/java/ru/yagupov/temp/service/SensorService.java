package ru.yagupov.temp.service;

import ru.yagupov.temp.entity.Sensor;
import ru.yagupov.temp.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository repository;

    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public List<Sensor> findAll() {
        return repository.findAll();
    }

    public Optional<Sensor> findById(Long id) {
        return repository.findById(id);
    }

    public Sensor save(Sensor sensor) {
        sensor.setLastUpdated(java.time.OffsetDateTime.now());
        return repository.save(sensor);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}