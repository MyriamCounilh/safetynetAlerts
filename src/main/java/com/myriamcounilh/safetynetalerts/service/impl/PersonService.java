package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.repository.IPersonRepository;
import com.myriamcounilh.safetynetalerts.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

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
        return repository.addPerson(person);
    }

    /**
     * Get
     */
    @Override
    public List<Person> getPerson() {
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
        return repository.deletePerson(personFound);
    }

}
