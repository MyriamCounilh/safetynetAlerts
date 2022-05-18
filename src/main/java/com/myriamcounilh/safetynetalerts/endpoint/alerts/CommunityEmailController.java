package com.myriamcounilh.safetynetalerts.endpoint.alerts;

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

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    private static final Logger logger = LogManager.getLogger(CommunityEmailController.class);

    private final IAlertsService alertsService;

    @Autowired
    public CommunityEmailController(IAlertsService alertsService) {
        this.alertsService = alertsService;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getCommunityEmail(@RequestParam String city) {
        logger.info("Get Mapping for community email it's OK");
        return new ResponseEntity<>(alertsService.getEmail(city), HttpStatus.OK);
    }

}
