package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Output;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OutputRepository extends JpaRepository<Output, UUID> {
    List<Output> findAllByDateBetween(Date from, Date to);

    List<Output> findAllByDate(Date date);
}

