package com.myriamcounilh.safetynetalerts.service;

import com.myriamcounilh.safetynetalerts.model.Firestation;

import java.util.List;

public interface IFirestationService {

   List<Firestation> getFirestations();

    Firestation addFirestation(Firestation firestation);

    Firestation modifyFirestation(Integer station, String address, Firestation firestation);

    Firestation deleteFirestation(Integer station, String address);

}
