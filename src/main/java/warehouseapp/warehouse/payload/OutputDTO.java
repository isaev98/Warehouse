package warehouseapp.warehouse.payload;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OutputDTO {
    private Date date;
    private Integer warehouseId, clientId, currencyId;
// ?????????
    public List<OutputProductDTO> getOutputProductDTOList() {
        return null;
    }
}
