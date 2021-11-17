package warehouseapp.warehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import warehouseapp.warehouse.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends AbsNameEntity {

    @ManyToOne
    private Category category;
    @OneToMany
    private List<Attachment> attachments;
    private String code;
    @OneToOne
    private Measurement measurement;

}
