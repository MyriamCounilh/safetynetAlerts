package com.myriamcounilh.safetynetalerts.service;

import com.myriamcounilh.safetynetalerts.model.Firestation;

import java.util.List;
import java.util.Map;

public interface IFirestationService {

    Map<Integer, List<String>> getFirestation();

    Firestation addFirestation(Firestation firestation);

    Firestation modifyFirestation(Integer station, String address, Firestation firestation);

    Firestation deleteFirestation(Integer station, String address);

}
