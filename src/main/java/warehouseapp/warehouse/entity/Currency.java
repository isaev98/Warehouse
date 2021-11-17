package warehouseapp.warehouse.entity;

import lombok.EqualsAndHashCode;
import warehouseapp.warehouse.entity.template.AbsNameEntity;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
public class Currency extends AbsNameEntity {

}
