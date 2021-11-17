package warehouseapp.warehouse.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Input;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.InputDTO;
import warehouseapp.warehouse.repository.InputRepository;
import warehouseapp.warehouse.service.InputService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/input")
public class InputController {
    final
    InputService inputService;
    final
    InputRepository inputRepository;

    public InputController(InputService inputService, InputRepository inputRepository) {
        this.inputService = inputService;
        this.inputRepository = inputRepository;
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody InputDTO inputDTO) throws ParseException {
        ApiResponse apiResponse = inputService.addInput(inputDTO);
        return ResponseEntity.status(apiResponse.isSuccess()
                        ? HttpStatus.CREATED :
                        HttpStatus.CONFLICT).
                body(apiResponse);

    }

    @GetMapping
    public HttpEntity<List<Input>> getAll() {
        return ResponseEntity.ok(inputRepository.findAll());
    }

}
