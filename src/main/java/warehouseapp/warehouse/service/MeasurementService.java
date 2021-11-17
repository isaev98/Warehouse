package warehouseapp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.Measurement;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class MeasurementService {

    final
    MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public ApiResponse edit(Integer id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) return new ApiResponse("NOT FOUND!", false);
        Measurement m = optionalMeasurement.get();
        if (measurement.getName() != null) m.setName(measurement.getName());
        m.setActive(measurement.isActive());
        measurementRepository.save(m);
        return new ApiResponse("UPDATED!", true);
    }

    public ApiResponse addMeasurement(Measurement measurement) {
        Measurement m = new Measurement();
        m.setName(measurement.getName());
        m.setActive(measurement.isActive());
        measurementRepository.save(m);
        return new ApiResponse("SAVED!", true);
    }
}
