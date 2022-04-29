package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.repository.IMedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {

    private final List<MedicalRecord> listMedicalRecord = new ArrayList<>();

    @Override
    public MedicalRecord getMedicalRecord(String firstName, String lastName) {
        Optional<MedicalRecord> medicalRecordOptional = listMedicalRecord.stream().filter(mr ->
                mr.getFirstName().equals(firstName) &&
                mr.getLastName().equals(lastName)).findFirst();
        return medicalRecordOptional.orElse(null);
    }

    @Override
    public MedicalRecord getMedicalRecord(MedicalRecord medicalRecord) {
        return getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        this.listMedicalRecord.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public List<MedicalRecord> getMedicalRecord() {
        return listMedicalRecord;
    }

    @Override
    public MedicalRecord modifyMedicalRecord(MedicalRecord medicalRecordFound, MedicalRecord medicalRecord) {
        this.listMedicalRecord.remove(medicalRecordFound);
        this.listMedicalRecord.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecordFound) {
        this.listMedicalRecord.remove(medicalRecordFound);
        return medicalRecordFound;
    }

}
