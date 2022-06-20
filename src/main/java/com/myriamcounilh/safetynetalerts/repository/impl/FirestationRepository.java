package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.repository.IFirestationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FirestationRepository implements IFirestationRepository {

    private static final Logger logger = LogManager.getLogger(FirestationRepository.class);

    private List<Firestation> listFirestation = new ArrayList<>();

    @Override
    public Firestation getFirestation(Integer station, String address) {
        Optional<Firestation> result = this.listFirestation.stream().filter(fire -> fire.getAddress().equals(address) && fire.getStation().equals(station))
                        .findFirst();
        logger.debug("Return firestationList with station and address");
        if (result.isPresent()) {
            return result.get();
        }
        else return null;
    }

    @Override
    public Firestation getFirestation(Firestation firestation) {
        logger.debug("Return getFirestation with getStation() and getAddress()");
        return getFirestation(firestation.getStation(), firestation.getAddress());
    }

    @Override
    public List<Firestation> getFirestation() {
        return listFirestation;
    }

    @Override
    public Firestation addFirestation(Firestation firestation) {
        this.listFirestation.add(firestation);
        logger.debug("Return addFirestation");
        return firestation;
    }

    @Override
    public Firestation modifyFirestation(Firestation firestationFound, Firestation firestation) {
        if (this.listFirestation.remove(firestationFound)) {
            this.listFirestation.add(firestation);
            logger.debug("Return modifyFirestation");
            return firestation;
        }
        return null;
    }

    @Override
    public Firestation deleteFirestation(Firestation firestationFound) {
        if (this.listFirestation.remove(firestationFound)) {
            logger.debug("Return deleteFirestation with modifyFirestation");
            return firestationFound;
        }
        return null;
    }
}
