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

    public void addPerson(Person person) {
        repository.addPerson(person);
    }

    public List<Person> getPerson() {
        return repository.getPerson();
    }


}
