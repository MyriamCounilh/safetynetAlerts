package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.service.IFirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    private final IFirestationService firestationService;

    @Autowired
    public FirestationController(IFirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @GetMapping
    public  ResponseEntity<List<Firestation>> getFirestation() {
        logger.info("Get Mapping for firestation it OK");
        return new ResponseEntity<>(firestationService.getFirestations(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Firestation> addFirestation(@Valid @RequestBody Firestation firestation) {
        Firestation firestationCreated = firestationService.addFirestation(firestation);
        if (firestationCreated != null) {
            logger.info("Post Mapping for firestation it's OK");
            return new ResponseEntity<>(firestationCreated, HttpStatus.OK);
        } else {
            logger.error("Post Mapping for firestation is error {}", firestation );
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Firestation existing");
        }
    }

    @PutMapping
    public ResponseEntity<Firestation> modifyFirestation(@RequestParam Integer station, @RequestParam String address, @Valid @RequestBody Firestation firestation) {
        Firestation firestationModify = firestationService.modifyFirestation(station, address, firestation);
        if (firestationModify !=null) {
            logger.info("Put Mapping for modify Firestation OK");
            return new ResponseEntity<>(firestationModify, HttpStatus.OK);
        } else {
            logger.error("Put Mapping modify Firestation is error {}", firestation );
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Firestation not existing");
        }
    }

    @DeleteMapping
    public void deleteFirestation(@RequestParam Integer station, @RequestParam String address) {
        if (firestationService.deleteFirestation(station, address) != null) {
            logger.info("Delete Mapping for delete Firestation OK");
        } else {
            logger.error("Delete Mapping is error Firestation {}", station);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Firestation not found");
        }
    }
}
