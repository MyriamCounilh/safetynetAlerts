package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.repository.IFirestationRepository;
import com.myriamcounilh.safetynetalerts.service.IFirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FirestationService implements IFirestationService {

    private static final Logger logger = LogManager.getLogger(FirestationService.class);

    private final IFirestationRepository repository;

    @Autowired
    public FirestationService(IFirestationRepository repository) {
        this.repository = repository;
    }

    /**
     * POST
     */
    public Firestation addFirestation(Firestation firestation) {
        if(repository.getFirestation(firestation) != null) {
            return null;
        }
        logger.debug("Return addFirestation");
        return repository.addFirestation(firestation);
    }

    /**
     * Get
     */
    @Override
    public Map<Integer, List<String>> getFirestations() {
        logger.debug("Return getFirestation with Map");
        return repository.getFirestation();
    }

    /**
     * PUT
     */
    @Override
    public Firestation modifyFirestation(Integer station, String address, Firestation firestation) {
        Firestation firestationFound = repository.getFirestation(station, address);
        if (firestationFound == null) {
            return null;
        }
        logger.debug("Return modifyFirestation with station and address");
        return repository.modifyFirestation(firestationFound, firestation);
    }


    /**
     * Delete
     */
    @Override
    public Firestation deleteFirestation(Integer station, String address) {
        Firestation firestationFound = repository.getFirestation(station, address);
        if (firestationFound == null) {
            return null;
        }
        logger.debug("Return deleteFirestation with firestationFound");
        return repository.deleteFirestation(firestationFound);
    }

}
