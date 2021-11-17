package warehouseapp.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.UserDto;
import warehouseapp.warehouse.repository.UserRepository;
import warehouseapp.warehouse.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    final
    UserRepository userRepository;

    final
    UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping
    public ApiResponse getAll() {
        return new ApiResponse("Succeed!", true, userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id) {
        return userService.getOne(id);
    }

    @GetMapping("/byWarehouseId/{id}")
    public ApiResponse byWarehouseId(@PathVariable Integer id) {
        return new ApiResponse("Mana", true, userRepository.findAllByWarehousesId(id));
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return userService.edit(id, userDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        if (!userRepository.existsById(id)) return new ApiResponse("Not Found", false);
        userRepository.deleteById(id);
        return new ApiResponse("Deleted!!", true);
    }
}
