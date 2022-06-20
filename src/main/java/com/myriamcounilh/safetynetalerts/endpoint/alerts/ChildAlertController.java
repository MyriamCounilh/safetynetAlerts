package com.myriamcounilh.safetynetalerts.endpoint.alerts;

import com.myriamcounilh.safetynetalerts.dto.ChildAlertDTO;
import com.myriamcounilh.safetynetalerts.service.alerts.IAlertsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController extends ChildAlertDTO {

    private static final Logger logger = LogManager.getLogger(ChildAlertController.class);

    private final IAlertsService alertsService;

    @Autowired
    public ChildAlertController(IAlertsService alertsService) {
        this.alertsService = alertsService;
    }

    @GetMapping()
    public ResponseEntity<ChildAlertDTO> getChildAlert(@RequestParam String adress) {
      logger.info("Get Mapping for childAlert it's OK");
      return new ResponseEntity<>(alertsService.getChildAlert(adress), HttpStatus.OK);
    }
}
