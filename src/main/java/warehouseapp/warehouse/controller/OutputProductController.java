package warehouseapp.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.OutputProduct;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.OutputProductDTO;
import warehouseapp.warehouse.repository.OutputProductRepository;
import warehouseapp.warehouse.service.OutputProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/outputProduct")
public class OutputProductController {

    final
    OutputProductService outputProductService;

    final
    OutputProductRepository outputProductRepository;

    public OutputProductController(OutputProductService outputProductService, OutputProductRepository outputProductRepository) {
        this.outputProductService = outputProductService;
        this.outputProductRepository = outputProductRepository;
    }

    @GetMapping("/list")
    public List<OutputProduct> list() {
        return outputProductRepository.findAll();
    }

    @PostMapping("/add")
    public ApiResponse save(@RequestBody OutputProductDTO outputProductDTO) {
        return outputProductService.save(outputProductDTO);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable UUID id, @RequestBody OutputProductDTO outputProductDTO) {
        return outputProductService.edit(id, outputProductDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable UUID id) {
        return outputProductService.delete(id);
    }

    @GetMapping("/byId/{id}")
    public ApiResponse byId(@PathVariable UUID id) {
        return outputProductService.byId(id);
    }


}
