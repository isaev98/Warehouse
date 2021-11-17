package warehouseapp.warehouse.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Category;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.CategoryDTO;
import warehouseapp.warehouse.repository.CategoryRepository;
import warehouseapp.warehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    final
    CategoryRepository categoryRepository;
    final
    CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody CategoryDTO categoryDTO) {
        ApiResponse apiResponse = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.status(apiResponse.isSuccess()
                        ? HttpStatus.CREATED :
                        HttpStatus.CONFLICT).
                body(apiResponse);
    }

    @GetMapping
    public HttpEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
//        return ResponseEntity.status(categoryService.getOne(id).isSuccess()
//                ? HttpStatus.OK
//                : HttpStatus.NOT_FOUND)
//                .body(categoryService.getOne(id));
        return ResponseEntity.ok(categoryService.getOne(id));
    }

    //older Parents
    @GetMapping("/parentList")
    public HttpEntity<?> getParents() {
        return ResponseEntity.ok(categoryService.getParents());
    }

    //Parent Id {id} childes
    @GetMapping("/byParentId/{id}")
    public HttpEntity<?> getByParentId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getByParentId(id));
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        ApiResponse response = categoryService.edit(id, categoryDTO);
        return ResponseEntity.status(response.isSuccess()
                ? HttpStatus.ACCEPTED
                : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.delete(id));
    }

}
