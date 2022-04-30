package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.endpoint.FirestationController;
import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.repository.IFirestationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FirestationRepository implements IFirestationRepository {

    private static final Logger logger = LogManager.getLogger(FirestationRepository.class);

    private final Map<Integer, List<String>> firestationMap = new HashMap<>();

    @Override
    public Firestation getFirestation(Integer station, String address) {
        List<String> firestationList = firestationMap.get(station);

        if (firestationList == null || !firestationList.contains(address)) {
            return null;
        }
        logger.debug("Return firestationList with station and address");
        return new Firestation(station, address);
    }

    @Override
    public Firestation getFirestation(Firestation firestation) {
        logger.debug("Not Return getFirestation with getStation() and getAddress()");
        return getFirestation(firestation.getStation(), firestation.getAddress());
    }

    @Override
    public Firestation addFirestation(Firestation firestation) {
        List<String> listStation;
        if(firestationMap.containsKey(firestation.getStation()) ) {
            listStation = firestationMap.get(firestation.getStation());
        } else {
            listStation = new ArrayList<>();
        }
        listStation.add(firestation.getAddress());
        this.firestationMap.put(firestation.getStation(), listStation);
        logger.debug("Not Return addFirestation");
        return firestation;
    }

    @Override
    public Map<Integer, List<String>> getFirestation() {
        return firestationMap;
    }

    @Override
    public Firestation modifyFirestation(Firestation firestationFound, Firestation firestation) {
        List<String> firestationList = firestationMap.get(firestationFound.getStation());
        if (firestationList == null) {
            return null;
        }
        firestationList.remove(firestationFound.getAddress());
        firestationList.add(firestation.getAddress());
        logger.debug("Not Return modifyFirestation");
        return firestation;
    }

    @Override
    public Firestation deleteFirestation(Firestation firestationFound) {
        List<String> firestationList = firestationMap.get(firestationFound.getStation());
        if (firestationList == null) {
            return null;
        }
        firestationList.remove(firestationFound.getAddress());
        logger.debug("Not Return firestationFound with deleteFirestation");
        return firestationFound;
    }

}
