package warehouseapp.warehouse.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface OutputProductProjection {

    @Value("#{target.productId}")
    Integer getProductId();

    @Value("#{target.outputId}")
    UUID getOutputId();

//    Double getAmount();
//    Double getPrice();

}
