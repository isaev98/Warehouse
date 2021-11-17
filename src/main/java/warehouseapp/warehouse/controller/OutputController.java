package warehouseapp.warehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.OutputDTO;
import warehouseapp.warehouse.service.OutputService;

@RestController
@RequestMapping("/api/output")
public class OutputController {
    final
    OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping
    public ApiResponse save(@RequestBody OutputDTO outputDTO) {
        return outputService.save(outputDTO);
    }
}
