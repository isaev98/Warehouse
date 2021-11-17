package warehouseapp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.Output;
import warehouseapp.warehouse.entity.OutputProduct;
import warehouseapp.warehouse.entity.Product;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.OutputProductDTO;
import warehouseapp.warehouse.repository.OutputProductRepository;
import warehouseapp.warehouse.repository.OutputRepository;
import warehouseapp.warehouse.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class OutputProductService {

    final
    OutputRepository outputRepository;

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    public OutputProductService(OutputRepository outputRepository) {
        this.outputRepository = outputRepository;
    }

    public ApiResponse save(OutputProductDTO outputProductDTO) {

        OutputProduct outputProduct = new OutputProduct();

        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());

        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Product not found", false);
        }
        outputProduct.setProduct(optionalProduct.get());

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new ApiResponse("Output not found", false);
        }
        outputProduct.setOutput(optionalOutput.get());

        outputProductRepository.save(outputProduct);

        return new ApiResponse("Saved", true, outputProduct);

    }

    public ApiResponse edit(UUID id, OutputProductDTO outputProductDTO) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);

        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("OutputProduct not found", false);
        }

        OutputProduct outputProduct = optionalOutputProduct.get();

        if (outputProductDTO.getAmount() != 0) {
            outputProduct.setAmount(outputProductDTO.getAmount());
        }

        if (outputProductDTO.getPrice() != 0) {
            outputProductDTO.setPrice(outputProductDTO.getPrice());
        }

        if (outputProductDTO.getOutputId() != null) {
            Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
            if (!optionalOutput.isPresent()) {
                return new ApiResponse("Output not found", false);
            }
            outputProduct.setOutput(optionalOutput.get());
        }

        if (outputProductDTO.getProductId() != null) {
            Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
            if (!optionalProduct.isPresent()) {
                return new ApiResponse("Product not found", false);
            }
            outputProduct.setProduct(optionalProduct.get());
        }

        outputProductRepository.save(outputProduct);
        return new ApiResponse("Saved", true, outputProduct);
    }

    public ApiResponse delete(UUID id) {

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);

        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("OutputProduct not found", false);
        }
        outputProductRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse byId(UUID id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);

        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("Output product not found", false);
        }

        return new ApiResponse("Found", true, optionalOutputProduct);
    }
}
