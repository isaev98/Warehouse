package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.InputProduct;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface InputProductRepository extends JpaRepository<InputProduct, UUID> {
    List<InputProduct> findAllByInputId(UUID id);

    List<InputProduct> findAllByExpireDateBefore(Date date);
}
