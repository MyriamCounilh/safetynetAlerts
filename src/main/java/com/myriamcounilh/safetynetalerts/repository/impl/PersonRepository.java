package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.repository.IPersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository implements IPersonRepository {

    private static final Logger logger = LogManager.getLogger(PersonRepository.class);

    private final List<Person> listPerson = new ArrayList<>();

    @Override
    public Person getPerson(String firstName, String lastName) {
        Optional<Person> personOptional = listPerson.stream().filter(person ->
                person.getFirstName().equals(firstName) &&
                person.getLastName().equals(lastName)).findFirst();
        logger.debug("Return Person with firstName and lastName");
        return personOptional.orElse(null);
    }

    @Override
    public Person getPerson(Person person) {
        logger.debug("Return Person with getFirstName() and getLastName()");
        return getPerson(person.getFirstName(), person.getLastName());
    }

    @Override
    public Person addPerson(Person person) {
        this.listPerson.add(person);
        logger.debug("Return addPerson");
        return person;
    }

    @Override
    public List<Person> getPerson() {
        logger.debug("Return getPerson with listPerson");
        return listPerson;
    }

    @Override
    public List<String> getPerson(String city) {
        logger.debug("Return getPerson with city");
        return getPerson(city);
    }

    @Override
    public Person modifyPerson(Person personFound, Person person) {
        if (this.listPerson.remove(personFound)) {
            this.listPerson.add(person);
            logger.debug("Return modifyPerson");
            return person;
        }
        return null;
    }

    @Override
    public Person deletePerson(Person personFound) {
        if (this.listPerson.remove(personFound)) {
            logger.debug("Return deletePerson with personFound");
            return personFound;
        }
        return null;
    }

}
