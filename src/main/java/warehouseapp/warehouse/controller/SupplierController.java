package warehouseapp.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Supplier;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.SupplierRepository;
import warehouseapp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    final
    SupplierRepository supplierRepository;
    final
    SupplierService supplierService;

    public SupplierController(SupplierRepository supplierRepository, SupplierService supplierService) {
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @GetMapping("/list")
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Integer id) {
        return supplierService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.update(id, supplier);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleted(@PathVariable Integer id) {
        if (!supplierRepository.existsById(id)) return new ApiResponse("Supplier not found!", false);
        supplierRepository.deleteById(id);
        return new ApiResponse("deleted !", true);
    }
}
