package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.repository.IPersonRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository implements IPersonRepository {

    private final List<Person> listPerson = new ArrayList<>();

    @Override
    public Person getPerson(String firstName, String lastName) {
        Optional<Person> personOptional = listPerson.stream().filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)).findFirst();
        return personOptional.orElse(null);
    }

    @Override
    public Person getPerson(Person person) {
        return getPerson(person.getFirstName(), person.getLastName());
    }

    @Override
    public Person addPerson(Person person) {
        this.listPerson.add(person);
        return person;
    }

    @Override
    public List<Person> getPerson() {
        return listPerson;
    }

    @Override
    public Person modifyPerson(Person personFound, Person person) {
        this.listPerson.remove(personFound);
        this.listPerson.add(person);
        return person;
    }

    @Override
    public Person deletePerson(Person personFound) {
        this.listPerson.remove(personFound);
        return personFound;
    }
}
