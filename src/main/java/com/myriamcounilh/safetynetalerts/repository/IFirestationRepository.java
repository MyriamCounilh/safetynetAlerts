package com.myriamcounilh.safetynetalerts.repository;

import com.myriamcounilh.safetynetalerts.model.Firestation;

import java.util.List;
import java.util.Map;

public interface IFirestationRepository {

    Firestation getFirestation(Integer station, String address);

    Firestation getFirestation(Firestation firestation);

    Map<Integer, List<String>> getFirestation();

    Firestation addFirestation(Firestation firestation);

    Firestation modifyFirestation(Firestation firestationFound, Firestation firestation);

    Firestation deleteFirestation(Firestation firestationFound);
}
