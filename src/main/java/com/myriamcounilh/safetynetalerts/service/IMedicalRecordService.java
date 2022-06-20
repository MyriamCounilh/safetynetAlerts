package com.myriamcounilh.safetynetalerts.service;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;

import java.util.Date;
import java.util.List;

public interface IMedicalRecordService {
    List<MedicalRecord> getMedicalRecord();

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord modifyMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord);

    MedicalRecord deleteMedicalRecord(String firstName, String lastName);

    MedicalRecord getMedicalByFirstnameAndLastName(String firstName, String lastName);

    Date getBirthdate();
}
