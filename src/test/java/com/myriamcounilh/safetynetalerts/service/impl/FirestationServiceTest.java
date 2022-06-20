package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.endpoint.FirestationController;
import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.service.IFirestationService;
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
public class FirestationServiceTest {

    //My mock
    @Mock
    private IFirestationService firestationService;

    //class to be tested
    private FirestationController firestationController;

    @BeforeEach
    void setUp() {
        firestationController = new FirestationController(firestationService);
    }

    @Test
    public void when_get_firestation_return_same_firestation() {
        //Given
        Firestation firestationTest = new Firestation();
        firestationTest.setStation(2);
        firestationTest.setAddress( "3 rue du champ");

        List<Firestation> Firestations = new ArrayList<>();
        Firestations.add(firestationTest);

        Mockito.when(firestationService.getFirestations()).thenReturn(Firestations);

        //When

        List<Firestation> firestationList = firestationController.getFirestation().getBody();

        //Then
        assertNotNull(firestationList);
        assertTrue(firestationList.contains(firestationTest));

    }

    @Test
    public void when_add_firestation_return_this_firestation() {
        //Given
        Firestation firestationTest = new Firestation();
        firestationTest.setStation(2);
        firestationTest.setAddress( "3 rue du champ");

        List<Firestation> Firestations = new ArrayList<>();
        Firestations.add(firestationTest);

        Mockito.when(firestationService.getFirestations()).thenReturn(Firestations);

        //When
        List<Firestation> firestationList = firestationController.getFirestation().getBody();

        //Then
        assertNotNull(firestationList);
        assertTrue(firestationList.contains(firestationTest));
    }

    @Test
    void when_add_not_firestation_not_existing() {
        //Given
        Firestation firestationRecordExisting = new Firestation();
        firestationRecordExisting.setStation(1);
        firestationRecordExisting.setAddress( "23 rue du champ");

        Firestation firestationRecordNotExisting = new Firestation();
        firestationRecordExisting.setStation(5);
        firestationRecordExisting.setAddress( "23 rue du champ");

        List<Firestation> Firestations = new ArrayList<>();
        Firestations.add(firestationRecordExisting);

        Mockito.when(firestationService.getFirestations()).thenReturn(Firestations);

        //When
        List<Firestation> firestationList = firestationController.getFirestation().getBody();

        //Then
        assertNotNull(firestationList);
        assertTrue(firestationList.contains(firestationRecordExisting));
        assertThrows(ResponseStatusException.class, () -> firestationController.addFirestation(firestationRecordNotExisting));
    }

    @Test
    void when_modify_firestation_return_firestation_modify() {
        //Given
        Firestation firestationModifyTrue = new Firestation();
        firestationModifyTrue.setStation(2);
        firestationModifyTrue.setAddress("rue richerie");

        Mockito.when(firestationService.modifyFirestation(1, "route de la perouse", firestationModifyTrue)).thenReturn(firestationModifyTrue);

        //When
        firestationController.modifyFirestation(1, "route de la perouse",firestationModifyTrue).getBody();

        //Then
        assertNotNull(firestationModifyTrue);
        assertEquals(2, firestationModifyTrue.getStation());
        assertEquals( "rue richerie", firestationModifyTrue.getAddress());
    }

    @Test
    void when_modify_firestation_return_firestation_not_existing() {
        //Given
        Firestation firestationNotModify = new Firestation();
        firestationNotModify.setStation(2);
        firestationNotModify.setAddress("rue richerie");

        Mockito.when(firestationService.modifyFirestation(1, "route de la perouse", firestationNotModify)).thenReturn(null);

        //When and Then
        assertThrows(ResponseStatusException.class, () -> firestationController.modifyFirestation(1, "route de la perouse",firestationNotModify));
    }

    @Test
    void when_delete_firestation() {
        //Given
        Firestation firestationDeleteExisting = new Firestation();
        firestationDeleteExisting.setStation(4);
        firestationDeleteExisting.setAddress("rue brue");

        Mockito.when(firestationService.deleteFirestation(4, "rue brue")).thenReturn(firestationDeleteExisting);

        //When
        firestationController.deleteFirestation(4, "rue brue");

        //Then
        assertNotNull(firestationDeleteExisting);
        assertEquals(4, firestationDeleteExisting.getStation());
        assertEquals( "rue brue", firestationDeleteExisting.getAddress());
    }

    @Test
    void when_delete_firestation_return_not_existing() {
        //Given
        Firestation firestationDeleteExisting = new Firestation();
        firestationDeleteExisting.setStation(1);
        firestationDeleteExisting.setAddress("rue brue");

        Mockito.when(firestationService.deleteFirestation(4, "rue brue")).thenReturn(null);

        //When and Then
        assertThrows(ResponseStatusException.class, () -> firestationController.deleteFirestation(4, "rue brue"));
    }
}
