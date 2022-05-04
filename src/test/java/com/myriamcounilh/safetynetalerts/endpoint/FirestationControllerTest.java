package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.repository.impl.FirestationRepository;
import com.myriamcounilh.safetynetalerts.service.impl.FirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FirestationControllerTest {

    @Test
    void when_get_Firestation_return_firestation() {
        //Given
        Firestation firestation = new Firestation(2, "8 street New York");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
        firestationController.addFirestation(firestation);

        //Then
        Map<Integer, List<String>> listMap = firestationController.getFirestation().getBody();
        assertNotNull(listMap);
        assertTrue(listMap.containsKey(firestation.getStation()));
    }

    @Test
    void when_add_firestation_return_this_firestation() {
        //Given
        Firestation firestationAddTest = new Firestation(4, "8 route du père noël");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));

        //Then
        assertEquals(firestationAddTest, firestationController.addFirestation(firestationAddTest).getBody());
    }

    @Test
    void when_add_firestation_not_existing() {
        //Given
        Firestation firestationAddNotTest = new Firestation(2, "8 route du père noël");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
        firestationController.addFirestation(firestationAddNotTest);

        //Then
        assertThrows(ResponseStatusException.class, () -> firestationController.addFirestation(firestationAddNotTest));
    }

    @Test
    void when_modify_firestation_return_firestation_modify() {
        //Given
        Firestation firestationModifyTest = new Firestation(2, "8 route du père noël");
        Firestation firestationNewNameTest = new Firestation(2, "24 rue du père noël");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
        firestationController.addFirestation(firestationModifyTest);
        firestationController.modifyFirestation(2,"8 route du père noël", firestationNewNameTest);

        //Then
        Map<Integer, List<String>> listMap = firestationController.getFirestation().getBody();
        assertNotNull(listMap);
        assertTrue(listMap.containsKey(firestationNewNameTest.getStation()));
    }

    @Test
    void when_modify_firestation_return_not_existing() {
        //Given
        Firestation firestationModifyNotTest = new Firestation(3, "4 route de pointe blanche");
        Firestation firestationNewNameTest = new Firestation(1, "254 rue de pointe rouge");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
        firestationController.addFirestation(firestationModifyNotTest);
        firestationController.modifyFirestation(3,"4 route de pointe blanche", firestationNewNameTest);

        //Then
        assertThrows(ResponseStatusException.class, () -> firestationController.modifyFirestation(3, "4 route de pointe blanche", firestationNewNameTest));
    }

    @Test
    void when_delete_firestation() {
        //Given
        Firestation firestationDeleteTest = new Firestation(3, "4 route de pointe blanche");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
        firestationController.addFirestation(firestationDeleteTest);
        firestationController.deleteFirestation(3,"4 route de pointe blanche");

        //Then
        assertThrows(ResponseStatusException.class, () -> firestationController.deleteFirestation(3, "4 route de pointe blanche"));
    }

    @Test
    void when_delete_firestation_return_not_existing() {
        //Given
        Firestation firestationDeleteNotTest = new Firestation(3, "4 route de pointe blanche");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
        firestationController.addFirestation(firestationDeleteNotTest);

        //Then
        assertThrows(ResponseStatusException.class, () -> firestationController.deleteFirestation(1, "254 rue de pointe rouge"));
    }

}
