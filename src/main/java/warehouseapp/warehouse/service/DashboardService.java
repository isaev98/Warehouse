package warehouseapp.warehouse.service;

import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.Input;
import warehouseapp.warehouse.entity.InputProduct;
import warehouseapp.warehouse.entity.Output;
import warehouseapp.warehouse.entity.OutputProduct;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.DailyDTO;
import warehouseapp.warehouse.repository.InputProductRepository;
import warehouseapp.warehouse.repository.InputRepository;
import warehouseapp.warehouse.repository.OutputProductRepository;
import warehouseapp.warehouse.repository.OutputRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DashboardService {
    final
    InputRepository inputRepository;
    final
    InputProductRepository inputProductRepository;
    final
    OutputRepository outputRepository;
    final
    OutputProductRepository outputProductRepository;

    public DashboardService(InputRepository inputRepository, InputProductRepository inputProductRepository, OutputRepository outputRepository, OutputProductRepository outputProductRepository) {
        this.inputRepository = inputRepository;
        this.inputProductRepository = inputProductRepository;
        this.outputRepository = outputRepository;
        this.outputProductRepository = outputProductRepository;
    }

    public ApiResponse getDaily(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        List<DailyDTO> dailyDTOList = new ArrayList<>();
        List<Input> allByDate = inputRepository.findAllByDate(date1);
        for (Input input : allByDate) {
            List<InputProduct> allByInputId = inputProductRepository.findAllByInputId(input.getId());
            for (InputProduct inputProduct : allByInputId) {
                DailyDTO dailyDTO = new DailyDTO();
                dailyDTO.setProductName(inputProduct.getProduct().getName());
                dailyDTO.setAmount(inputProduct.getAmount());
                dailyDTO.setSum(inputProduct.getAmount() * inputProduct.getPrice());
                dailyDTOList.add(dailyDTO);
            }
        }
        return new ApiResponse("Mana", true, dailyDTOList);
    }

    public ApiResponse getDailyOutput(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        List<DailyDTO> dailyDTOList = new ArrayList<>();
        List<Output> allByDate = outputRepository.findAllByDate(d);
        for (Output output : allByDate) {
            List<OutputProduct> allByOutputId = outputProductRepository.findAllByOutputId(output.getId());
            for (OutputProduct outputProduct : allByOutputId) {
                DailyDTO dailyDTO = new DailyDTO();
                dailyDTO.setAmount(outputProduct.getAmount());
                dailyDTO.setSum(dailyDTO.getAmount() * outputProduct.getPrice());
                dailyDTO.setProductName(dailyDTO.getProductName());
                dailyDTOList.add(dailyDTO);
            }
        }
        return new ApiResponse("Mana", true, dailyDTOList);
    }


    public ApiResponse notification(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        LocalDate localDate = LocalDate.of(date1.getYear(), date1.getMonth(), date1.getDay());
        localDate = localDate.plusDays(3);

        ZoneId zoneId = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Date from = Date.from(zonedDateTime.toInstant());

        List<InputProduct> dateBefore = inputProductRepository.findAllByExpireDateBefore(from);

        return new ApiResponse("Mana", true, dateBefore);

    }
}
