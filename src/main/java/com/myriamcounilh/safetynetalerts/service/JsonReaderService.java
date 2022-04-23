package com.myriamcounilh.safetynetalerts.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myriamcounilh.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonReaderService {

    private final PersonService personService;

    private final static String PATH_TO_JSON_FILE = "data.json";

    public JsonReaderService(@Autowired PersonService personService) throws IOException {
        this.personService = personService;

        /**
         * create object mapper instance
         */
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(JsonReaderService.class.getClassLoader().getResourceAsStream(PATH_TO_JSON_FILE));
        loadPersons(node, mapper);

        /**
         * TODO
         * loadMedicalRecord();
         * loadFirstation();
         */
    }

    private void loadPersons(JsonNode nodeParent, ObjectMapper mapper) throws JsonProcessingException {
        JsonNode nodePersons = nodeParent.get("persons");
        for (int n = 0; n < nodePersons.size(); n++) {
            JsonNode nodePerson = nodePersons.get(n);
            Person person = mapper.readValue(nodePerson.toString(), Person.class);
            personService.addPerson(person);
        }
    }
}
