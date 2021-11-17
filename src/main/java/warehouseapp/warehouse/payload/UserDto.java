package warehouseapp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private List<Integer> warehousesId;
}
