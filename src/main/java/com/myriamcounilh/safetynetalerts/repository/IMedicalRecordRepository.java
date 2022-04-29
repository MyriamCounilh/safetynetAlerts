package com.myriamcounilh.safetynetalerts.repository;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordRepository {

    MedicalRecord getMedicalRecord(String firstName, String lastName);

    MedicalRecord getMedicalRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> getMedicalRecord();

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord modifyMedicalRecord(MedicalRecord medicalRecordFound, MedicalRecord medicalRecord);

    MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecordFound);
}
