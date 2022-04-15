package com.myriamcounilh.safetynetalerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.Map;

public class JsonReaderService {

    public static void main(String[] args) {
        JsonReaderService app = new JsonReaderService();

        String fileName = "data.json";

        System.out.println("getResource : " + fileName);
        URL url = app.getFileFromResource(fileName);

        app.fileJson(url);
    }

    private URL getFileFromResource(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return resource;
        }

    }

    void fileJson(URL url) {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON file to map
            Map<?, ?> map = mapper.readValue(url, Map.class);

            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }
 }
