package com.myriamcounilh.safetynetalerts.endpoint.alerts;


import com.myriamcounilh.safetynetalerts.service.alerts.IAlertsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    private static final Logger logger = LogManager.getLogger(PhoneAlertController.class);

    private final IAlertsService alertsService;


    public PhoneAlertController(IAlertsService alertsService) {
        this.alertsService = alertsService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getPhoneAlert(@RequestParam Integer firestation) {
        logger.info("Get Mapping for phone alert it's OK");
        return new ResponseEntity<>(alertsService.getPhoneAlert(firestation), HttpStatus.OK);
    }
}
