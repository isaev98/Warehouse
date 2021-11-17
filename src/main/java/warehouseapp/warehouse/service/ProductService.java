package warehouseapp.warehouse.service;

import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.*;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.ProductDTO;
import warehouseapp.warehouse.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    final
    ProductRepository productRepository;
    final
    CategoryRepository categoryRepository;
    final
    MeasurementRepository measurementRepository;
    final
    AttachmentContentRepository attachmentContentRepository;
    final
    AttachmentRepository attachmentRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, MeasurementRepository measurementRepository, AttachmentContentRepository attachmentContentRepository, AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.measurementRepository = measurementRepository;
        this.attachmentContentRepository = attachmentContentRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public ApiResponse save(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode(UUID.randomUUID().toString());

        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        if (!categoryOptional.isPresent()) return new ApiResponse("Not found categoryId!", false);
        product.setCategory(categoryOptional.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent()) return new ApiResponse("MeasurementId not found!", false);
        product.setMeasurement(optionalMeasurement.get());

////        List<Attachment> allById = attachmentRepository.findAllById(productDTO.getAttachmentIds());
//        product.setAttachments(allById);

        productRepository.save(product);
        return new ApiResponse("Save", true);
    }

    public ApiResponse getByCatId(Integer catId) {
        return new ApiResponse("Mana", true, productRepository.findAllByCategoryId(catId));
    }

//    public ApiResponse searchByName(String name) {
//        return new ApiResponse("Mana", true, productRepository.findByNameLike("%" + name + "%"));
//    }

    public ApiResponse edit(Integer id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Product Id not found", false);
        }
        Product product = optionalProduct.get();
        List<Attachment> allById = attachmentRepository.findAllById(productDTO.getAttachmentIds());
        product.setAttachments(allById);
        product.setName(productDTO.getName());
        Optional<Category> byId = categoryRepository.findById(productDTO.getCategoryId());
        product.setCategory(byId.get());
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        product.setMeasurement(optionalMeasurement.get());
        List<Attachment> attachments = attachmentRepository.findAllById(productDTO.getAttachmentIds());
        product.setAttachments(attachments);
        product.setCode(productDTO.getCode());
        productRepository.save(product);
        return new ApiResponse("Saved", true);
    }
    public ApiResponse changeStatus(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new ApiResponse("Not found", false);
        optionalProduct.get().setActive(!optionalProduct.get().isActive());
        return new ApiResponse("Changed Status !", true);
    }


}
