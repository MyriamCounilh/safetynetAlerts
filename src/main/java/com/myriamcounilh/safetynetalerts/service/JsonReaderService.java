package com.myriamcounilh.safetynetalerts.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonReaderService {

    private final static String PATH_TO_JSON_FILE = "data.json";

    public JsonReaderService() throws IOException {

        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(JsonReaderService.class.getClassLoader().getResourceAsStream(PATH_TO_JSON_FILE));

        System.out.println(node);

        /**
         * TODO
         * loadPersons(node, mapper);
         * loadMedicalRecord();
         * loadFirstation();
         */
    }

}
