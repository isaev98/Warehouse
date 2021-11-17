package warehouseapp.warehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import warehouseapp.warehouse.entity.template.AbsNameEntity;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Supplier extends AbsNameEntity {
    private String phoneNumber;

}
