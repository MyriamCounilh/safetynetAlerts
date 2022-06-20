package com.myriamcounilh.safetynetalerts.repository.impl;

import com.myriamcounilh.safetynetalerts.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonRepositoryTest {


    @Test
    void when_add_person_return_the_person_then_the_list() {
        //Given
        Person personTest = new Person();

        //When
        PersonRepository personRepository = new PersonRepository();
        Person result = personRepository.addPerson(personTest);

        //Then
        List<Person> personList = personRepository.getPerson();
        assertNotNull(personList);
        assertTrue(personList.contains(personTest));
    }

    @Test
    void when_get_person_record_return_the_person_then_the_firstname_and_the_lastname() {
        //Given
        Person personTest = new Person("Paul", "DUTRONC");

        //When
        PersonRepository personRepository = new PersonRepository();
        personRepository.addPerson(personTest);

        //Then
        List<Person> personList = personRepository.getPerson();
        assertNotNull(personList);
        assertTrue(personList.contains(personTest));

    }

    @Test
    void when_get_person() {
        //Given
        Person person = new Person("Julie", "DUTRONC");

        //When
        PersonRepository personRepository = new PersonRepository();
        personRepository.addPerson(person);
        personRepository.getPerson("Julie", "DUTRONC");
        personRepository.getPerson("Justine", "DUTRONC");
        personRepository.getPerson("Justine", "DUPONT");

        //Then
        assertNotNull(personRepository.getPerson("Julie", "DUTRONC"));
        assertNull(personRepository.getPerson("Justine", "DUTRONC"));
        assertNull(personRepository.getPerson("Justine", "DUPONT"));
    }

    @Test
    void when_modify_person_return_the_medical_record_modify() {
        //Given
        Person personModifyTest = new Person("Pierre", "NOIR");
        Person personNewNameTest = new Person("Pascal", "NOIR");

        //When
        PersonRepository personRepository = new PersonRepository();
        personRepository.addPerson(personModifyTest);
        personRepository.modifyPerson(personModifyTest, personNewNameTest);

        //Then
        List<Person> personList = personRepository.getPerson();
        assertNotNull(personList);
        assertTrue(personList.contains(personNewNameTest));
        assertFalse(personList.contains(personModifyTest));
    }

    @Test
    void when_modify_person_not_existing() {
        //Given
        Person personModifyNotTest = new Person("Pierre", "NOIR");
        Person personNewNameTest = new Person("Pascal", "NOIR");

        //When
        PersonRepository personRepository = new PersonRepository();
        personRepository.addPerson(personModifyNotTest);

        //Then
        assertNotNull(personRepository.modifyPerson(personModifyNotTest, personNewNameTest));
        assertNull(personRepository.modifyPerson(personModifyNotTest, personModifyNotTest));
    }

    @Test
    void when_delete_person() {
        //Given
        Person personDeleteTest = new Person("Patrick", "FOUL");

        //When
        PersonRepository personRepository = new PersonRepository();
        personRepository.addPerson(personDeleteTest);

        //Then
        assertNotNull(personRepository.deletePerson(personDeleteTest));
        assertNull(personRepository.deletePerson(personDeleteTest));
    }

}
