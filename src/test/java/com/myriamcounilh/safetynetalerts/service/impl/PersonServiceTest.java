package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.repository.IPersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    //My mock
    @Mock
    private IPersonRepository personRepository;

    //class to be tested
    private PersonService personService;

    @BeforeEach
    void setUp() {

        personService = new PersonService(personRepository);
    }

    @Test
    public void getPersonShouldReturnSamePerson() {

        //Given
        Person person = new Person();
        person.setFirstName("Thomas");
        person.setLastName("BLANC");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        Mockito.when(personRepository.getPerson()).thenReturn(persons);

        //When
        List<Person> result = personService.getPerson();
        //Then
        assertEquals(result.get(0).getFirstName(), person.getFirstName());

    }

    @Test
    void modifyPersonShouldReturnModifiedPerson() {

        //Given
        Person personModifyTest = new Person();
        personModifyTest.setFirstName("Jean");
        personModifyTest.setLastName("Brun");

        Person personNewNameTest = new Person();
        personNewNameTest.setFirstName("Dupont");
        personNewNameTest.setLastName("Jean");

        Mockito.when(personRepository.getPerson("Jean", "Brun")).thenReturn(personModifyTest);
        Mockito.when(personRepository.modifyPerson(personModifyTest, personNewNameTest)).thenReturn(personNewNameTest);
        //When
        Person modifiedPerson = personService.modifyPerson("Jean", "Brun", personNewNameTest);

        //Then
        assertNotNull(modifiedPerson);
        assertEquals("Dupont", modifiedPerson.getFirstName());
        assertEquals("Jean", modifiedPerson.getLastName());
    }

    @Test
    void deletePersonShouldReturnDeletedPerson() {

        //Given
        Person personDeleteTest = new Person();
        personDeleteTest.setFirstName("Paul");
        personDeleteTest.setLastName("Belmondo");

        Mockito.when(personRepository.getPerson("Paul", "Belmondo")).thenReturn(personDeleteTest);
        Mockito.when(personRepository.deletePerson(personDeleteTest)).thenReturn(personDeleteTest);

        //When
        Person deletedPerson = personService.deletePerson("Paul", "Belmondo");

        //Then
        assertNotNull(deletedPerson);
        assertEquals("Paul", deletedPerson.getFirstName());
        assertEquals("Belmondo", deletedPerson.getLastName());
    }
}
