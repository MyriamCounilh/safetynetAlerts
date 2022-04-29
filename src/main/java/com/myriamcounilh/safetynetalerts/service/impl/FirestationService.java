package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.repository.IFirestationRepository;
import com.myriamcounilh.safetynetalerts.service.IFirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FirestationService implements IFirestationService {

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
        return repository.addFirestation(firestation);
    }

    /**
     * Get
     */
    @Override
    public Map<Integer, List<String>> getFirestation() {
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
        return repository.modifyFirestation(firestationFound, firestation);
    }

    /**
     * DELETE
     */
    @Override
    public Firestation deleteFirestation(Integer station, String address) {
        Firestation firestationFound = repository.getFirestation(station, address);
        if (firestationFound == null) {
            return null;
        }
        return repository.deleteFirestation(firestationFound);
    }

}
