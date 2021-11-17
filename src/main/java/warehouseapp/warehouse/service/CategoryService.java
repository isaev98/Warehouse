package warehouseapp.warehouse.service;

import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.Category;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.CategoryDTO;
import warehouseapp.warehouse.projection.CategoryProjection;
import warehouseapp.warehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    final
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse saveCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParentId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentId());
            category.setParentCategory(optionalCategory.get());
        } else {
            category.setParentCategory(null);
        }
        categoryRepository.save(category);
        return new ApiResponse("Saved!", true);
    }

    public ApiResponse getParents() {
        List<Category> categoryNull = categoryRepository.findAllByParentCategoryIsNull();
        return new ApiResponse("Success", true, categoryNull);
    }

    public ApiResponse getByParentId(Integer id) {
        List<CategoryProjection> allByParentCategoryId = categoryRepository.findAllByParentCategoryId(id);
        return new ApiResponse("Success", true, allByParentCategoryId);
    }

    public ApiResponse edit(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) return new ApiResponse("Not Found", false);

        Category edit = categoryOptional.get();
        if (categoryDTO.getName() != null) {
            edit.setName(categoryDTO.getName());
        }
        if (categoryDTO.getParentId() != null) {
            Optional<Category> byId = categoryRepository.findById(categoryDTO.getParentId());
            edit.setParentCategory(byId.get());
        }
//        if (categoryDTO.getParentId() != 0) {
//        } else {
//            edit.setParentCategory(null);
//        }
        categoryRepository.save(edit);
        return new ApiResponse("Edited!", true);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.map(category -> new ApiResponse("Mana", true, category)).orElseGet(() -> new ApiResponse("NOT FOUND!", false));
    }

    public ApiResponse delete(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
        return new ApiResponse("Success!", true);
    }
}
