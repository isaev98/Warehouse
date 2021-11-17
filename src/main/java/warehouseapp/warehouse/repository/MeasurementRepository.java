package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
