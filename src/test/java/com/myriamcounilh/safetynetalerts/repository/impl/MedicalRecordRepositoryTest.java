package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.endpoint.MedicalRecordController;
import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.repository.IMedicalRecordRepository;
import com.myriamcounilh.safetynetalerts.service.impl.MedicalRecordService;
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
    void when_get_Medical_Record_return_the_Medical_record_then_the_list() {
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
    void when_get_Medical_Record_return_the_Medical_record_then_the_firstname_and_the_lastname() {
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
    void addMedicalRecord() {
    }

    @Test
    void modifyMedicalRecord() {
    }

    @Test
    void deleteMedicalRecord() {
    }
}
