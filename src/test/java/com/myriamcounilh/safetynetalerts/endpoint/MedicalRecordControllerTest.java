package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.repository.impl.MedicalRecordRepository;
import com.myriamcounilh.safetynetalerts.service.impl.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordControllerTest {

    @Test
    void when_get_medical_record() {
        //Given
        MedicalRecord medicalRecordTest = new MedicalRecord();

        //When
        MedicalRecordController medicalRecordController = new MedicalRecordController(new MedicalRecordService(new MedicalRecordRepository()));
        medicalRecordController.addMedicalRecord(medicalRecordTest);

        //Then
        List<MedicalRecord> medicalRecordList = medicalRecordController.getMedicalRecord().getBody();
        assertNotNull(medicalRecordList);
        assertTrue(medicalRecordList.contains(medicalRecordTest));
    }

    @Test
    void when_add_medical_record_return_this_medical_record() {
        //Given
        MedicalRecord medicalRecordTest = new MedicalRecord("Luc", "Noir");

        //When
        MedicalRecordController medicalRecordController = new MedicalRecordController(new MedicalRecordService(new MedicalRecordRepository()));

        //Then
        assertEquals(medicalRecordTest, medicalRecordController.addMedicalRecord(medicalRecordTest).getBody());
    }

    @Test
    void when_not_add_medical_record_not_existing() {
        //Given
        MedicalRecord medicalRecordNotTest = new MedicalRecord("David", "Blanc");

        //When
        MedicalRecordController medicalRecordController = new MedicalRecordController(new MedicalRecordService(new MedicalRecordRepository()));
        medicalRecordController.addMedicalRecord(medicalRecordNotTest);

        //Then
        assertThrows(ResponseStatusException.class, () -> medicalRecordController.addMedicalRecord(medicalRecordNotTest));

    }

    @Test
    void when_modify_medical_record_return_medical_record_modify() {
        //Given
        MedicalRecord medicalRecordModifyTest = new MedicalRecord("Hello", "World");
        MedicalRecord medicalRecordNewNameTest = new MedicalRecord("Good", "Bye");

        //When
        MedicalRecordController medicalRecordController = new MedicalRecordController(new MedicalRecordService(new MedicalRecordRepository()));
        medicalRecordController.addMedicalRecord(medicalRecordModifyTest);
        medicalRecordController.modifyMedicalRecord("Hello", "World", medicalRecordNewNameTest);

        //Then
        List<MedicalRecord> medicalRecordList = medicalRecordController.getMedicalRecord().getBody();
        assertNotNull(medicalRecordList);
        assertTrue(medicalRecordList.contains(medicalRecordNewNameTest));
        assertFalse(medicalRecordList.contains(medicalRecordModifyTest));
    }

    @Test
    void when_modify_medical_record_not_existing() {
        //Given
        MedicalRecord medicalRecordModifyNotTest = new MedicalRecord("Hello", "World");
        MedicalRecord medicalRecordNewNameTest = new MedicalRecord("Good", "Bye");

        //When
        MedicalRecordController medicalRecordController = new MedicalRecordController(new MedicalRecordService(new MedicalRecordRepository()));
        medicalRecordController.addMedicalRecord(medicalRecordModifyNotTest);
        medicalRecordController.modifyMedicalRecord("Hello", "World", medicalRecordNewNameTest);

        //Then
        assertThrows(ResponseStatusException.class, () ->
                    medicalRecordController.modifyMedicalRecord("Hello", "World", medicalRecordModifyNotTest));
    }

    @Test
    void when_delete_medical_record() {
        //Given
        MedicalRecord medicalRecordDeleteTest = new MedicalRecord("Jean", "Claude");

        //When
        MedicalRecordController medicalRecordController = new MedicalRecordController(new MedicalRecordService(new MedicalRecordRepository()));
        medicalRecordController.addMedicalRecord(medicalRecordDeleteTest);
        medicalRecordController.deleteMedicalRecord("Jean", "Claude");

        //Then
        assertThrows(ResponseStatusException.class, () ->  medicalRecordController.deleteMedicalRecord("Jean", "Claude"));
    }

    @Test
    void when_delete_medical_record_return_not_existing() {
        //Given
        MedicalRecord medicalRecordDeleteTest = new MedicalRecord("David", "Lucas");

        //When
        MedicalRecordController medicalRecordController = new MedicalRecordController(new MedicalRecordService(new MedicalRecordRepository()));
        medicalRecordController.addMedicalRecord(medicalRecordDeleteTest);

        //Then
        assertThrows(ResponseStatusException.class, () ->  medicalRecordController.deleteMedicalRecord("Jean", "Lucas"));
    }
}
