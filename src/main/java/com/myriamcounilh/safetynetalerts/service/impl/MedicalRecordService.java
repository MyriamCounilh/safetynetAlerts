package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.repository.IMedicalRecordRepository;
import com.myriamcounilh.safetynetalerts.service.IMedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {

    private static final Logger logger = LogManager.getLogger(MedicalRecordService.class);

    private final IMedicalRecordRepository repository;

    @Autowired
    public MedicalRecordService(IMedicalRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * Get
     */
     @Override
     public List<MedicalRecord> getMedicalRecord() {
         logger.debug("Return getMedicalRecord with List");
            return repository.getMedicalRecord();
     }

    /**
     * Post
     */
    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        if (repository.getMedicalRecord(medicalRecord) != null) {
            return null;
        }
        logger.debug("Return addMedicalRecord");
        return repository.addMedicalRecord(medicalRecord);
    }

    /**
     * PUT
     */
    @Override
    public MedicalRecord modifyMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord) {
        MedicalRecord medicalRecordFound = repository.getMedicalRecord(firstName, lastName);
        if (medicalRecordFound == null) {
            return null;
        }
        logger.debug("Return modifyMedicalRecord with station and address");
        return repository.modifyMedicalRecord(medicalRecordFound, medicalRecord);
    }

    /**
     * DELETE
     */
    @Override
    public MedicalRecord deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecordFound = repository.getMedicalRecord(firstName, lastName);
        if (medicalRecordFound == null) {
            return null;
        }
        logger.debug("Return deleteMedicalRecord with firstName and lastName");
        return repository.deleteMedicalRecord(medicalRecordFound);
    }

    @Override
    public MedicalRecord getMedicalByFirstnameAndLastName(String firstName, String lastName) {
        return repository.getMedicalRecord(firstName, lastName);
    }

    @Override
    public Date getBirthdate() {
        return null;
    }

}
