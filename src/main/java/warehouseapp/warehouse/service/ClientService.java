package warehouseapp.warehouse.service;

import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.Client;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    final
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public ApiResponse add(Client client) {
        Client c = new Client();
        c.setName(client.getName());
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
            return new ApiResponse("Tel bor bunaqa!", false);
        c.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client);
        return new ApiResponse("Added", true);

    }

    public ApiResponse edit(Integer id, Client client) {
        Optional<Client> byId = clientRepository.findById(id);

        if (!byId.isPresent()) return new ApiResponse("Not Found", false);
        Client editing = byId.get();
        if (client.getName() != null) editing.setName(client.getName());
        if (client.getPhoneNumber() != null && !editing.getPhoneNumber().equals(client.getPhoneNumber())) {
            if (!clientRepository.existsByPhoneNumber(client.getPhoneNumber())) return new ApiResponse("Xato", false);
            editing.setPhoneNumber(client.getPhoneNumber());
        }
        clientRepository.save(editing);
        return new ApiResponse("Success", true);
    }
}
