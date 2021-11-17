package warehouseapp.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Measurement;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.MeasurementRepository;
import warehouseapp.warehouse.service.MeasurementService;

import java.util.Optional;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    final
    MeasurementRepository measurementRepository;

    final
    MeasurementService measurementService;

    public MeasurementController(MeasurementRepository measurementRepository, MeasurementService measurementService) {
        this.measurementRepository = measurementRepository;
        this.measurementService = measurementService;
    }

    @GetMapping("/list")
    public ApiResponse getAll() {
        return new ApiResponse("FOUND!", true, measurementRepository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.map(measurement -> new ApiResponse("FOUND!", true, measurement)).orElseGet(() -> new ApiResponse("NOT FOUND!", false));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        if (measurementRepository.existsById(id)) {
            measurementRepository.deleteById(id);
        } else {
            return new ApiResponse("NOT FOUND!", true);
        }
        return new ApiResponse("DELETED!", true);
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Measurement measurement) {
        return measurementService.edit(id, measurement);
    }
}
