package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.repository.IPersonRepository;
import com.myriamcounilh.safetynetalerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    private final IPersonRepository repository;

    @Autowired
    public PersonService(IPersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Post
     */
    @Override
    public Person addPerson(Person person) {
        if (repository.getPerson(person) != null) {
            return null;
        }
        logger.debug("Return addPerson");
        return repository.addPerson(person);
    }

    /**
     * Get
     */
    @Override
    public List<Person> getPerson() {
        logger.debug("Return getPerson with List");
        return repository.getPerson();
    }

    /**
     * PUT
     */
    @Override
    public Person modifyPerson(String firstName, String lastName, Person person) {
        Person personFound = repository.getPerson(firstName, lastName);
        if (personFound == null) {
            return null;
        }
        logger.debug("Return modifyPerson");
        return repository.modifyPerson(personFound, person);
    }

    /**
     * DELETE
     */
    @Override
    public Person deletePerson(String firstName, String lastName) {
        Person personFound = repository.getPerson(firstName, lastName);
        if (personFound == null) {
            return null;
        }
        logger.debug("Return deletePerson with personFound");
        return repository.deletePerson(personFound);
    }

}
