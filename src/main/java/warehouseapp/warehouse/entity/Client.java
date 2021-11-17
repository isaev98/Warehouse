package warehouseapp.warehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import warehouseapp.warehouse.entity.template.AbsEntity;
import warehouseapp.warehouse.entity.template.AbsNameEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Client extends AbsNameEntity {
    private String phoneNumber;
}
