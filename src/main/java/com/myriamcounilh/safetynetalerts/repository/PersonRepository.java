package com.myriamcounilh.safetynetalerts.repository;

import com.myriamcounilh.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private List<Person> listPerson = new ArrayList<>();

    public Person addPerson(Person person) {
        this.listPerson.add(person);
        return person;
    }

    public List<Person> getPerson() {
        return listPerson;
    }

}
