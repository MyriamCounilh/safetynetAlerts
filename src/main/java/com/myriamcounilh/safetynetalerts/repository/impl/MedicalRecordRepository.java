package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.repository.IMedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {

    private static final Logger logger = LogManager.getLogger(MedicalRecordRepository.class);

    private final List<MedicalRecord> listMedicalRecord = new ArrayList<>();

    @Override
    public MedicalRecord getMedicalRecord(String firstName, String lastName) {
        Optional<MedicalRecord> medicalRecordOptional = listMedicalRecord.stream().filter(mr ->
                mr.getFirstName().equals(firstName) &&
                mr.getLastName().equals(lastName)).findFirst();
        logger.debug("Not Return MedicalRecord with firstName and lastName");
        return medicalRecordOptional.orElse(null);
    }

    @Override
    public MedicalRecord getMedicalRecord(MedicalRecord medicalRecord) {
        logger.debug("Not Return MedicalRecord with getFirstName() and getLastName()");
        return getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        this.listMedicalRecord.add(medicalRecord);
        logger.debug("Not Return addMedicalRecord");
        return medicalRecord;
    }

    @Override
    public List<MedicalRecord> getMedicalRecord() {
        logger.debug("Not Return List<MedicalRecord> for getMedicalRecord");
        return listMedicalRecord;
    }

    @Override
    public MedicalRecord modifyMedicalRecord(MedicalRecord medicalRecordFound, MedicalRecord medicalRecord) {
        this.listMedicalRecord.remove(medicalRecordFound);
        this.listMedicalRecord.add(medicalRecord);
        logger.debug("Not Return modifyMedicalRecord");
        return medicalRecord;
    }

    @Override
    public MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecordFound) {
        this.listMedicalRecord.remove(medicalRecordFound);
        logger.debug("Not Return deleteMedicalRecord with medicalRecordFound");
        return medicalRecordFound;
    }

}
