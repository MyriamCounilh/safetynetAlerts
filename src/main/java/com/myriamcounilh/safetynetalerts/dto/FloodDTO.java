package com.myriamcounilh.safetynetalerts.dto;

import com.myriamcounilh.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FloodDTO {

    private String address;

    private String lastName;

    private String phone;

    private Integer age;

    private String[] medications;

    private String[] allergies;


    /***
     * TODO
     * Liste de tous les foyers desservis par la caserne.
     * Elle regroupe les personnes par adresse
     * Elle contient le nom, le téléphone, l'âge des habitants et les antécédents médicaux (médicaments, posologie et allergies)
     */
}
