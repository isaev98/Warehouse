package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Input;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface InputRepository extends JpaRepository<Input, UUID> {
    List<Input> findAllByDateBetween(Date from, Date to);

    List<Input> findAllByDate(Date date);


}
