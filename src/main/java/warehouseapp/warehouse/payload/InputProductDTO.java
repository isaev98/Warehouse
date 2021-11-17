package warehouseapp.warehouse.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDTO {
    private Integer productId;
    private double price,amount;
    private Date expireDate;
}
