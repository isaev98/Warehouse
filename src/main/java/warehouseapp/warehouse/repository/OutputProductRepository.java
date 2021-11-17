package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Input;
import warehouseapp.warehouse.entity.InputProduct;
import warehouseapp.warehouse.entity.Output;
import warehouseapp.warehouse.entity.OutputProduct;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OutputProductRepository extends JpaRepository<OutputProduct, UUID> {
//    List<OutputProduct> findAllByDateBetween(Date from, Date to);
    List<OutputProduct> findAllByOutputId(UUID id);

//    List<OutputProduct> findAllByDate(Date date);
}

