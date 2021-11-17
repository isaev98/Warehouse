package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Category;
import warehouseapp.warehouse.projection.CategoryProjection;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByParentCategoryIsNull();

    List<CategoryProjection> findAllByParentCategoryId(Integer id);


}
