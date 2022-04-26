package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.service.IMedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO : http://localhost:8080/medicalRecord
 * Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete HTTP :
 * ● ajouter un dossier médical ;
 * ● mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le
 * prénom et le nom de famille ne changent pas) ;
 * ● supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme
 * identificateur unique).
 */

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

    private final IMedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(IMedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getMedicalRecord() {
        logger.info("Get Mapping it OK For MedicalRecord");
        return new ResponseEntity<>(medicalRecordService.getMedicalRecord(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> addMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord medicalRecordCreated = medicalRecordService.addMedicalRecord(medicalRecord);
        if (medicalRecordCreated != null) {
            logger.info("Post Mapping for medicalRecord it OK");
            return new ResponseEntity<>(medicalRecordCreated, HttpStatus.OK);
        } else {
          logger.error("Post Mapping for medicalRecord is error {}", medicalRecord );
          throw new ResponseStatusException(HttpStatus.CONFLICT, "MedicalRecord existing");
        }
    }

    @PutMapping
    public ResponseEntity<MedicalRecord> modifyMedicalRecord(@RequestParam String firstName, String lastName, @Valid @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord medicalRecordModify = medicalRecordService.modifyMedicalRecord(firstName, lastName, medicalRecord);
        if (medicalRecordModify != null) {
            logger.info("Put Mapping for modify medicalRecord OK");
            return new ResponseEntity<>(medicalRecordModify, HttpStatus.OK);
        } else {
            logger.error("Put Mapping is error {}", medicalRecord);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MedicalRecord not existing");
        }
    }

    @DeleteMapping
    public void deleteMedicalRecord(@RequestParam String firstName, String lastName) {
        if (medicalRecordService.deleteMedicalRecord(firstName, lastName) != null) {
            logger.info("Delete Mapping for supprime medicalRecord OK");
        } else {
            logger.error("Delete Mapping is error {} - {}", firstName, lastName );
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "medicalRecord not found");
        }
    }
}
