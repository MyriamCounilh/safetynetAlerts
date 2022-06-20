package com.myriamcounilh.safetynetalerts.repository;

import com.myriamcounilh.safetynetalerts.model.Firestation;

import java.util.List;

public interface IFirestationRepository {

    Firestation getFirestation(Integer station, String address);

    Firestation getFirestation(Firestation firestation);

    List<Firestation> getFirestation();

    Firestation addFirestation(Firestation firestation);

    Firestation modifyFirestation(Firestation firestationFound, Firestation firestation);

    Firestation deleteFirestation(Firestation firestationFound);
}
