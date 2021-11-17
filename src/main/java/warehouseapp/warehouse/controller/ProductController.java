package warehouseapp.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Product;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.ProductDTO;
import warehouseapp.warehouse.repository.CategoryRepository;
import warehouseapp.warehouse.repository.ProductRepository;
import warehouseapp.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    final
    CategoryRepository categoryRepository;
    final
    ProductRepository productRepository;
    final
    ProductService productService;

    public ProductController(CategoryRepository categoryRepository, ProductRepository productRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @PostMapping
    public ApiResponse save(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @GetMapping("/{catId}")
    public ApiResponse getByCatId(@PathVariable Integer catId) {
        return productService.getByCatId(catId);
    }

    @GetMapping("/list")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        productService.edit(id, productDTO);
        return new ApiResponse("Edited!", true);
    }

    @GetMapping("/changeStatus/{id}")
    public ApiResponse changeStatus(@PathVariable Integer id) {
        return productService.changeStatus(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleted(@PathVariable Integer id) {
        if (!productRepository.existsById(id)) return new ApiResponse("Not Found!", false);
        productRepository.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }
}
