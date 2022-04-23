package com.myriamcounilh.safetynetalerts.service;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    /**
     * Post
     * @param person
     */
    public Person addPerson(Person person) {
        return repository.addPerson(person);
    }

    /**
     * Get
     * @return
     */
    public List<Person> getPerson() {
        return repository.getPerson();
    }

    /**
     * PUT
     */
    public Person modifyPerson(String firstName, String lastName,Person person) {

       return repository.modifyPerson(firstName, lastName, person);
    }



}
