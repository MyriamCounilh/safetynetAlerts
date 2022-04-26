package com.myriamcounilh.safetynetalerts.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myriamcounilh.safetynetalerts.model.MedicalRecord;
import com.myriamcounilh.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonReaderService {

    private final IPersonService personService;
    private final IMedicalRecordService medicalRecordService;


    private final static String PATH_TO_JSON_FILE = "data.json";

    public JsonReaderService(@Autowired IPersonService personService, @Autowired IMedicalRecordService medicalRecordService) throws IOException {
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;

        /**
         * create object mapper instance
         */
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(JsonReaderService.class.getClassLoader().getResourceAsStream(PATH_TO_JSON_FILE));
        loadPersons(node, mapper);
        loadMedicalRecords(node, mapper);

        /**
         * TODO
         * loadFirstation();
         */
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

}
