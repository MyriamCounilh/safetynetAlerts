package com.myriamcounilh.safetynetalerts.service;

import com.myriamcounilh.safetynetalerts.model.Person;

import java.util.List;

public interface IPersonService {

    List<Person> getPerson();

    Person addPerson(Person person);

    Person modifyPerson(String firstName, String lastName, Person person);

    Person deletePerson(String firstName, String lastName);

    List<Person> getPersonByAdress(String address);

    List<Person> getFamilyMember(String address, String lastname);
}
