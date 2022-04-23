package com.myriamcounilh.safetynetalerts.repository;

import com.myriamcounilh.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private List<Person> listPerson = new ArrayList<>();


    public Person addPerson(Person person) {

        for (Person p : listPerson) {
            if (p.equals(person)) {
                return null;
            }
        }
        this.listPerson.add(person);
        return person;
    }

    public List<Person> getPerson() {
        return listPerson;
    }

    public Person modifyPerson(String firstName, String lastName, Person person) {
        Person personFound = null;
        for (Person p : listPerson) {
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                personFound = p;
                break;
            }
        }
        if (personFound == null) {
            return null;
        }
        listPerson.remove(personFound);
        listPerson.add(person);

        return person;
    }
}
