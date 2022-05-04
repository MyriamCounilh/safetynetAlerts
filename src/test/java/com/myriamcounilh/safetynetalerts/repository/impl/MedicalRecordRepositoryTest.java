package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void when_get_medical_record_return_the_medical_record_then_the_list() {
        //Given
        MedicalRecord medicalRecordTest = new MedicalRecord();

        //When
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordRepository.addMedicalRecord(medicalRecordTest);

        //Then
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getMedicalRecord();
        assertNotNull(medicalRecordList);
        assertTrue(medicalRecordList.contains(medicalRecordTest));
    }

    @Test
    void when_get_medical_record_return_the_medical_record_then_the_firstname_and_the_lastname() {
        //Given
        MedicalRecord medicalRecordTest = new MedicalRecord("Pierre", "Dupont");

        //When
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordRepository.addMedicalRecord(medicalRecordTest);

        //Then
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getMedicalRecord();
        assertNotNull(medicalRecordList);
        assertTrue(medicalRecordList.contains(medicalRecordTest));
    }

    @Test
    void when_get_medical_record() {
        //Given
        MedicalRecord medicalRecord = new MedicalRecord("Pierre", "Dupont");

        //When
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        medicalRecordRepository.getMedicalRecord("Pierre", "Dupont");
        medicalRecordRepository.getMedicalRecord("Paul", "Dupont");
        medicalRecordRepository.getMedicalRecord("Pierre", "Durant");

        //Then
        assertNotNull(medicalRecordRepository.getMedicalRecord("Pierre", "Dupont"));
        assertNull(medicalRecordRepository.getMedicalRecord("Paul", "Dupont"));
        assertNull(medicalRecordRepository.getMedicalRecord("Pierre", "Durant"));
    }

    @Test
    void when_modifyMedicalRecord_return_the_medical_record_modify() {
        //Given
        MedicalRecord medicalRecordModifyTest = new MedicalRecord("Pierre", "Dupont");
        MedicalRecord medicalRecordNewNameTest = new MedicalRecord("Jacques", "Dupont");

        //When
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordRepository.addMedicalRecord(medicalRecordModifyTest);
        medicalRecordRepository.modifyMedicalRecord(medicalRecordModifyTest, medicalRecordNewNameTest);

        //Then
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getMedicalRecord();
        assertNotNull(medicalRecordList);
        assertTrue(medicalRecordList.contains(medicalRecordNewNameTest));
        assertFalse(medicalRecordList.contains(medicalRecordModifyTest));
    }

    @Test
    void when_modifyMedicalRecord_not_existing() {
        //Given
        MedicalRecord medicalRecordModifyNotTest = new MedicalRecord("Pierre", "Dupont");
        MedicalRecord medicalRecordNewNameTest = new MedicalRecord("Jacques", "Dupont");

        //When
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordRepository.addMedicalRecord(medicalRecordModifyNotTest);

        //Then
        assertNotNull(medicalRecordRepository.modifyMedicalRecord(medicalRecordModifyNotTest, medicalRecordNewNameTest));
        assertNull(medicalRecordRepository.modifyMedicalRecord(medicalRecordModifyNotTest, medicalRecordModifyNotTest));

    }

    @Test
    void when_delete_medical_record_return_() {
        //Given
        MedicalRecord medicalRecordDeleteTest = new MedicalRecord("Pierre", "Dupont");

        //When
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordRepository.addMedicalRecord(medicalRecordDeleteTest);

        //Then
        assertNotNull(medicalRecordRepository.deleteMedicalRecord(medicalRecordDeleteTest));
        assertNull(medicalRecordRepository.deleteMedicalRecord(medicalRecordDeleteTest));
    }


}
