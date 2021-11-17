package warehouseapp.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Client;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.ClientRepository;
import warehouseapp.warehouse.service.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    final
    ClientRepository clientRepository;

    final
    ClientService clientService;

    public ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping("/list")
    public List<Client> clients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id) {
        Optional byId = clientRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Not Found!", false);
        return new ApiResponse("Found!", true, byId.get());
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Client client) {
        return clientService.add(client);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.edit(id, client);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        Optional byId = clientRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Not Found!", false);

        clientRepository.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }
}
