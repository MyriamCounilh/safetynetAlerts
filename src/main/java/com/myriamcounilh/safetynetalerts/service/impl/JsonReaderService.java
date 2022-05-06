package com.myriamcounilh.safetynetalerts.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myriamcounilh.safetynetalerts.model.Firestation;
import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.service.IFirestationService;
import com.myriamcounilh.safetynetalerts.service.IMedicalRecordService;
import com.myriamcounilh.safetynetalerts.service.IPersonService;
import com.myriamcounilh.safetynetalerts.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonReaderService implements IReaderService {

    private final IPersonService personService;
    private final IMedicalRecordService medicalRecordService;
    private final IFirestationService firestationService;

    private final static String PATH_TO_JSON_FILE = "data.json";

    @Autowired
    public JsonReaderService(IPersonService personService,
                             IMedicalRecordService medicalRecordService,
                             IFirestationService firestationService) throws IOException {
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.firestationService = firestationService;

        /**
         * create object mapper instance
         */
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(JsonReaderService.class.getClassLoader().getResourceAsStream(PATH_TO_JSON_FILE));
        loadPersons(node, mapper);
        loadMedicalRecords(node, mapper);
        loadFirestations(node, mapper);
    }

    private void loadPersons(JsonNode nodeParent, ObjectMapper mapper) throws JsonProcessingException {
        JsonNode nodePersons = nodeParent.get("persons");
        for (JsonNode nodePerson : nodePersons) {
            Person person = mapper.readValue(nodePerson.toString(), Person.class);
            personService.addPerson(person);
        }
    }

    private void loadMedicalRecords(JsonNode nodeParent, ObjectMapper mapper) throws JsonProcessingException {
        JsonNode nodeMedicalRecords = nodeParent.get("medicalrecords");
        for (JsonNode nodeMedicalRecord : nodeMedicalRecords) {
            MedicalRecord medicalRecord = mapper.readValue(nodeMedicalRecord.toString(), MedicalRecord.class);
            medicalRecordService.addMedicalRecord(medicalRecord);
        }
    }

    private void loadFirestations(JsonNode nodeParent, ObjectMapper mapper) throws JsonProcessingException {
        JsonNode nodeFirestations = nodeParent.get("firestations");
        for (JsonNode nodeFirestation : nodeFirestations) {
            Firestation firestation = mapper.readValue(nodeFirestation.toString(), Firestation.class);
            firestationService.addFirestation(firestation);
        }
    }
}
