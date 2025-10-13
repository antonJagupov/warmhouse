package ru.yagupov.temp.repository;

import ru.yagupov.temp.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
