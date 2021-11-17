package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
