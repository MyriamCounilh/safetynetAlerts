package com.myriamcounilh.safetynetalerts.repository;

import com.myriamcounilh.safetynetalerts.model.Person;

import java.util.List;

public interface IPersonRepository {

    Person getPerson(String firstName, String lastName);

    Person getPerson(Person person);

    List<Person> getPerson();

    Person addPerson(Person person);

    Person modifyPerson(Person personFound, Person person);

    Person deletePerson(Person personFound);
}
