package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.repository.IMedicalRecordRepository;
import com.myriamcounilh.safetynetalerts.service.IMedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {

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
        return repository.deleteMedicalRecord(medicalRecordFound);
    }

}
