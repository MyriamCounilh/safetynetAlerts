package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.model.Firestation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FirestationRepositoryTest {

    @Test
    void when_add_firestation_return_the_firestation_then_the_list() {
        //Given
        Firestation firestationTest = new Firestation();

        //When
        FirestationRepository firestationRepository = new FirestationRepository();
        firestationRepository.addFirestation(firestationTest);

        //Then
        List<Firestation> firestationList = firestationRepository.getFirestation();
        assertNotNull(firestationList);
        assertTrue(firestationList.contains(firestationTest));
    }

    @Test
    void when_get_firestation_return_the_firestation_then_the_station_and_the_address() {
        //Given
        Firestation firestationTest = new Firestation(3, "lotissement le blanc");

        //When
        FirestationRepository firestationRepository = new FirestationRepository();
        firestationRepository.addFirestation(firestationTest);

        //Then
        List<Firestation> firestationList = firestationRepository.getFirestation();
        assertNotNull(firestationList);
        assertTrue(firestationList.contains(firestationTest));
    }

    @Test
    void when_get_firestation() {
        //Given
        Firestation firestationTest = new Firestation(3, "lotissement le blanc");
        FirestationRepository firestationRepository = new FirestationRepository();
        firestationRepository.addFirestation(firestationTest);

        //Then + when
        assertNotNull(firestationRepository.getFirestation(3, "lotissement le blanc"));
        assertNull(firestationRepository.getFirestation(2, "rue blanc"));
        assertNull(firestationRepository.getFirestation(5, "rien"));
    }

    @Test
    void when_modify_firestation_return_the_firestation_modify() {
        //Given
        Firestation firestationModifyTest = new Firestation(2, "lotissement le blanc");
        Firestation firestationNewNameTest = new Firestation(3, "route de la pointe blanche");

        //When
        FirestationRepository firestationRepository = new FirestationRepository();
        firestationRepository.addFirestation(firestationModifyTest);
        firestationRepository.modifyFirestation(firestationModifyTest, firestationNewNameTest);

        //Then
        List<Firestation> firestationList = firestationRepository.getFirestation();
        assertNotNull(firestationList);
        assertTrue(firestationList.contains(firestationNewNameTest));
        assertFalse(firestationList.contains(firestationModifyTest));
    }

    @Test
    void when_modify_firestation_not_existing() {
        //Given
        Firestation firestationModifyNotTest = new Firestation(2, "lotissement le blanc");
        Firestation firestationNewNameTest = new Firestation(3, "route de la pointe blanche");

        //When
        FirestationRepository firestationRepository = new FirestationRepository();
        firestationRepository.addFirestation(firestationModifyNotTest);

        //Then
        assertNotNull(firestationRepository.modifyFirestation(firestationModifyNotTest, firestationNewNameTest));
        assertNull(firestationRepository.modifyFirestation(firestationModifyNotTest, firestationModifyNotTest));
    }

    @Test
    void when_delete_firestation() {
        //Given
        Firestation firestationDeleteTest = new Firestation(2, "lotissement le blanc");

        //When
        FirestationRepository firestationRepository = new FirestationRepository();
        firestationRepository.addFirestation(firestationDeleteTest);

        //Then
        assertNotNull(firestationRepository.deleteFirestation(firestationDeleteTest));
        assertNull(firestationRepository.deleteFirestation(firestationDeleteTest));
    }
}
