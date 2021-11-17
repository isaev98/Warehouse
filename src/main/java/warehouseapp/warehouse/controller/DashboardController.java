package warehouseapp.warehouse.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.service.DashboardService;

import java.text.ParseException;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    final
    DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/daily")
    public HttpEntity<?> getDailyInputProduct(@RequestParam String date) throws ParseException {
        return ResponseEntity.ok(dashboardService.getDaily(date));
    }

    @GetMapping("/notification")
    public ApiResponse getNotification(@RequestParam String date) throws ParseException {
        return dashboardService.notification(date);
    }

    @GetMapping("/dailyOutput")
    public HttpEntity<?> getDailyOutputProduct(@RequestParam String date) throws ParseException {
        return ResponseEntity.ok(dashboardService.getDailyOutput(date));
    }
}
