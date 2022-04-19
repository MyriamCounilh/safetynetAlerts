package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * TODO : http://localhost:8080/firestation
 * Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
 * ● ajout d'un mapping caserne/adresse ;
 * ● mettre à jour le numéro de la caserne de pompiers d'une adresse ;
 * ● supprimer le mapping d'une caserne ou d'une adresse.
 */

public class FirestationController {

    @Autowired
    private FirestationService firestationService;


    public void addFirestation(@RequestBody Firestation firestation) {

        firestationService.addFirstation();
    }
}
