package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
