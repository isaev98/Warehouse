package warehouseapp.warehouse.payload;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InputDTO {
    private Date date;
    private Integer warehouseId,
            supplierId,
            currencyId;
    private List<InputProductDTO> inputProductDTOList;
}
