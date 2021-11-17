package warehouseapp.warehouse.projection;

import org.springframework.beans.factory.annotation.Value;

public interface CategoryProjection {

    String getName();

    @Value("#{target.parentCategory.id}")
    Integer getParentId();
}
