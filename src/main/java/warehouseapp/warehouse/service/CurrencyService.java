package warehouseapp.warehouse.service;

import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.Currency;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrencyService {
    final
    CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public ApiResponse addCurrency(Currency currency) {
        Currency currency1 = new Currency();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new ApiResponse("Saved!", true);
    }

    public ApiResponse getById(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.map(currency -> new ApiResponse("Found", true, currency)).orElseGet(() -> new ApiResponse("Not found currency", false));
    }

    public ApiResponse edit(Integer id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) return new ApiResponse("Not found Currency!", false);
        Currency edit = optionalCurrency.get();
        if (currency.getName() != null) {
            edit.setName(currency.getName());
        }
        currencyRepository.save(edit);
        return new ApiResponse("Updated!", true);
    }

    public ApiResponse changeStatus(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) return new ApiResponse("Not found", false);
        optionalCurrency.get().setActive(!optionalCurrency.get().isActive());
        return new ApiResponse("Changed Status !", true);
    }
}
