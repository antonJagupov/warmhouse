package ru.yagupov.temp.controller;

import ru.yagupov.temp.entity.Sensor;
import ru.yagupov.temp.service.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @GetMapping("/temperature")
    public String temperature(@RequestParam(required = false, name = "location") String location) {
        return String.valueOf(Math.round(Math.random()*100));
    }
    
    @GetMapping("/temperature/{sensorId}")
    public String temperatureById(@PathVariable(required = false, name = "sensorId") String sensorId) {
        return String.valueOf(Math.round(Math.random()*100));
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    private final SensorService service;

    public Controller(SensorService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/sensors")
    public List<Sensor> getAll() {
        return service.findAll();
    }

    @GetMapping("/api/v1/sensors/{id}")
    public Sensor getById(@PathVariable Long id) {
        return service.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found"));
    }

    @PostMapping("/api/v1/sensors")
    public Sensor create(@RequestBody Sensor sensor) {
        return service.save(sensor);
    }

    @PutMapping("/api/v1/sensors/{id}")
    public Sensor update(@PathVariable Long id, @RequestBody Sensor sensor) {
        Sensor existing = service.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found"));
        existing.setName(sensor.getName());
        existing.setType(sensor.getType());
        existing.setLocation(sensor.getLocation());
        existing.setValue(sensor.getValue());
        existing.setUnit(sensor.getUnit());
        existing.setStatus(sensor.getStatus());
        existing.setLastUpdated(java.time.OffsetDateTime.now());
        return service.save(existing);
    }

    @DeleteMapping("/api/v1/sensors/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
