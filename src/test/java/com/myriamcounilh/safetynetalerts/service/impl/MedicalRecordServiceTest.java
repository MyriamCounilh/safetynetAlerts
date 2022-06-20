package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.endpoint.MedicalRecordController;
import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.service.IMedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {

    //My mock
    @Mock
    private IMedicalRecordService medicalRecordService;

    //class to be tested
    private MedicalRecordController medicalRecordController;

    @BeforeEach
    void setUp() {
        medicalRecordController = new MedicalRecordController(medicalRecordService);
    }

    @Test
    void when_get_medicalRecord_return_medicalRecord_of_list() {
        //Given
        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setFirstName("Franck");
        medicalRecordTest.setLastName("DUPONT");
        List<MedicalRecord> MedicalRecords = new ArrayList<>();
        MedicalRecords.add(medicalRecordTest);
        Mockito.when(medicalRecordService.getMedicalRecord()).thenReturn(MedicalRecords);

        //When
        List<MedicalRecord> listMedicalRecord = medicalRecordController.getMedicalRecord().getBody();

        //Then
        assertNotNull(listMedicalRecord);
        assertTrue(listMedicalRecord.contains(medicalRecordTest));
    }

    @Test
    void when_add_medicalRecord_return_this_medicalRecord() {
        //Given
        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setFirstName("Francis");
        medicalRecordTest.setLastName("LALANNE");

        List<MedicalRecord> MedicalRecords = new ArrayList<>();
        MedicalRecords.add(medicalRecordTest);

        Mockito.when(medicalRecordService.getMedicalRecord()).thenReturn(MedicalRecords);

        //When
        List<MedicalRecord> listMedicalRecord = medicalRecordController.getMedicalRecord().getBody();

        //Then
        assertNotNull(listMedicalRecord);
        assertTrue(listMedicalRecord.contains(medicalRecordTest));
    }

    @Test
    void when_add_not_medicalRecord_not_existing() {
        //Given
        MedicalRecord medicalRecordExisting = new MedicalRecord();
        medicalRecordExisting.setFirstName("Francis");
        medicalRecordExisting.setLastName("LALANNE");

        MedicalRecord MedicalRecordNotExisting = new MedicalRecord();
        MedicalRecordNotExisting.setFirstName("Françoise");
        MedicalRecordNotExisting.setLastName("BLANC");

        List<MedicalRecord> MedicalRecords = new ArrayList<>();
        MedicalRecords.add(medicalRecordExisting);

        Mockito.when(medicalRecordService.getMedicalRecord()).thenReturn(MedicalRecords);

        //When
        List<MedicalRecord> listMedicalRecord = medicalRecordController.getMedicalRecord().getBody();

        //Then
        assertNotNull(listMedicalRecord);
        assertTrue(listMedicalRecord.contains(medicalRecordExisting));
        assertThrows(ResponseStatusException.class, () -> medicalRecordController.addMedicalRecord(MedicalRecordNotExisting));
    }

    @Test
    void when_modify_medicalRecord_return_medicalRecord_modify() {
        //Given
        MedicalRecord medicalRecordModifyTrue = new MedicalRecord();
        medicalRecordModifyTrue.setFirstName("Mireille");
        medicalRecordModifyTrue.setLastName("LOIRE");

        Mockito.when(medicalRecordService.modifyMedicalRecord("Myriam","LUCAS", medicalRecordModifyTrue)).thenReturn(medicalRecordModifyTrue);

        //When
        medicalRecordController.modifyMedicalRecord("Myriam","LUCAS", medicalRecordModifyTrue).getBody();

        //Then
        assertNotNull(medicalRecordModifyTrue);
        assertEquals("Mireille", medicalRecordModifyTrue.getFirstName());
        assertEquals("LOIRE", medicalRecordModifyTrue.getLastName());
    }

    @Test
    void when_modify_medicalRecord_return_medicalRecord_not_existing() {
        //Given
        MedicalRecord medicalRecordNotModify = new MedicalRecord();
        medicalRecordNotModify.setFirstName("Myriam");
        medicalRecordNotModify.setLastName("LUCAS");

        Mockito.when(medicalRecordService.modifyMedicalRecord("Miriame","LEMAINE", medicalRecordNotModify)).thenReturn(null);

        //When and Then
        assertThrows(ResponseStatusException.class, () -> medicalRecordController.modifyMedicalRecord("Miriame","LEMAINE", medicalRecordNotModify));
    }

    @Test
    void when_delete_medicalRecord() {
        //Given
        MedicalRecord medicalRecordDeleteExisting = new MedicalRecord();
        medicalRecordDeleteExisting.setFirstName("Paul");
        medicalRecordDeleteExisting.setLastName("BELMONT");

        Mockito.when(medicalRecordService.deleteMedicalRecord("Paul", "BELMONT")).thenReturn(medicalRecordDeleteExisting);

        //When
        medicalRecordController.deleteMedicalRecord("Paul", "BELMONT");

        //Then
        assertNotNull(medicalRecordDeleteExisting);
        assertEquals("Paul", medicalRecordDeleteExisting.getFirstName());
        assertEquals("BELMONT", medicalRecordDeleteExisting.getLastName());
    }

    @Test
    void When_delete_medicalRecord_return_not_existing() {
        //Given
        MedicalRecord medicalRecordDeleteNotExisting = new MedicalRecord();
        medicalRecordDeleteNotExisting.setFirstName("Julie");
        medicalRecordDeleteNotExisting.setLastName("Dupont");

        Mockito.when(medicalRecordService.deleteMedicalRecord("Miriame","LEMAINE")).thenReturn(null);

        //When and Then
        assertThrows(ResponseStatusException.class, () -> medicalRecordController.deleteMedicalRecord("Miriame","LEMAINE"));
    }
}
