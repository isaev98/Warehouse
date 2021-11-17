package warehouseapp.warehouse.entity;

import lombok.EqualsAndHashCode;
import warehouseapp.warehouse.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
public class Warehouse extends AbsNameEntity {



}
