package warehouseapp.warehouse.service;

import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.Warehouse;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class WarehouseService {
    final
    WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public ApiResponse getOne(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
//        1-Method
//        if (!optionalWarehouse.isPresent()) return new ApiResponse("NOT found!", false);
//        return new ApiResponse("Mana", true, optionalWarehouse.get());

//        2-Method
        return optionalWarehouse.map(warehouse -> new ApiResponse("Mana", true, warehouse)).orElseGet(() -> new ApiResponse("Not Found!", false));
    }

    public ApiResponse edit(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) return new ApiResponse("Not Found!", false);
        Warehouse edit = optionalWarehouse.get();
        if (warehouse.getName() != null) edit.setName(warehouse.getName());
        warehouseRepository.save(edit);
        return new ApiResponse("Edited!", true);
    }

    public ApiResponse changeStatus(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) return new ApiResponse("NOT", false);
//        if (optionalWarehouse.get().isActive()) {
//            optionalWarehouse.get().setActive(false);
//        } else {
//            optionalWarehouse.get().setActive(true);
//        }
//        return new ApiResponse("Changed!", true);
//
        optionalWarehouse.get().setActive(!optionalWarehouse.get().isActive());
        warehouseRepository.save(optionalWarehouse.get());
        return new ApiResponse("Changed!", true);
    }
}
