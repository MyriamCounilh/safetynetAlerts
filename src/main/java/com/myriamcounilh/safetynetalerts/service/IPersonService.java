package com.myriamcounilh.safetynetalerts.service;

import com.myriamcounilh.safetynetalerts.model.Person;

import java.util.List;

public interface IPersonService {
    Person addPerson(Person person);

    List<Person> getPerson();

    Person modifyPerson(String firstName, String lastName, Person person);

    Person deletePerson(String firstName, String lastName);
}
