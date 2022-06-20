package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.repository.impl.FirestationRepository;
import com.myriamcounilh.safetynetalerts.service.impl.FirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirestationControllerTest {

   @Test
    void when_get_Firestation() {
        //Given
        Firestation firestation = new Firestation();

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
        firestationController.addFirestation(firestation);

        //Then
        List<Firestation> listFirestation = firestationController.getFirestation().getBody();
        assertNotNull(listFirestation);
        assertTrue(listFirestation.contains(firestation));
    }

    @Test
    void when_add_firestation_return_this_firestation() {
        //Given
        Firestation firestationTest = new Firestation(4, "8 route du père noël");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));

        //Then
        assertEquals(firestationTest, firestationController.addFirestation(firestationTest).getBody());
    }

    @Test
    void when_not_add_firestation_record_not_existing() {
//        //Given
//        Firestation firestationNotTest = new Firestation(2, "8 route du père noël");
//
//        //When
//        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
//        firestationController.addFirestation(firestationNotTest);
//
//        //Then
//        assertThrows(ResponseStatusException.class, () -> firestationController.addFirestation(firestationNotTest));
   }

    @Test
    void when_modify_firestation_return_firestation_modify() {
//        //Given
//        Firestation firestationModifyTest = new Firestation(2, "8 route du père noël");
//        Firestation firestationNewTest =  new Firestation(3, "8 route du père noël");
//
//        //When
//        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
//        firestationController.addFirestation(firestationModifyTest);
//        firestationController.modifyFirestation(2, "8 route du père noël", firestationNewTest);
//
//        //Then
//        List<Firestation> listFirestation = firestationController.getFirestation().getBody();
//        assertNotNull(listFirestation);
//        assertTrue(listFirestation.contains(firestationNewTest));
//        assertFalse(listFirestation.contains(firestationModifyTest));
    }

    @Test
    void when_modify_firestation_return_not_existing() {
//        //Given
//        Firestation firestationModifyNoTest = new Firestation(1, "8 rue du père noël");
//        Firestation firestationNewTest =  new Firestation(4, "10 rue de la prairie");
//
//        //When
//        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
//        firestationController.addFirestation(firestationModifyNoTest);
//        firestationController.modifyFirestation(1, "8 rue du père noël", firestationNewTest);
//
//        //Then
//        assertThrows(ResponseStatusException.class, () -> firestationController.modifyFirestation(1, "8 rue du père noël", firestationModifyNoTest));
    }

    @Test
    void when_delete_firestation() {
//        //Given
//        Firestation firestationDeleteTest = new Firestation(3, "4 route de pointe blanche");
//
//        //When
//        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
//        firestationController.addFirestation(firestationDeleteTest);
//        firestationController.deleteFirestation(3, "4 route de pointe blanche");
//
//
//        //Then
//        assertThrows(ResponseStatusException.class, () -> firestationController.deleteFirestation(3, "4 route de pointe blanche"));
    }

    @Test
    void when_delete_firestation_return_not_existing() {
        //Given
        Firestation firestationDeleteTest = new Firestation(3, "4 route de pointe blanche");

        //When
        FirestationController firestationController = new FirestationController(new FirestationService(new FirestationRepository()));
        firestationController.addFirestation(firestationDeleteTest);
        //Then
        assertThrows(ResponseStatusException.class, () -> firestationController.deleteFirestation(5, "4 route de pointe blanche"));
    }

}
