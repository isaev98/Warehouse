package warehouseapp.warehouse.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class OutputProductDTO {
    private Integer productId;
    private double amount,price;
    private UUID outputId;
}
