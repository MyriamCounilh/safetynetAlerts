package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.service.IPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    //My mock
    @Mock
    private IPersonService personService;

    //class to be tested
    private PersonController personController;

    @BeforeEach
    void setUp() {
        personController = new PersonController(personService);
    }

    @Test
    void when_get_person_return_person_of_list() {
        //Given
        Person personTest = new Person();
        personTest.setFirstName("Riri");
        personTest.setLastName("BLANC");
        List<Person> persons = new ArrayList<>();
        persons.add(personTest);
        Mockito.when(personService.getPerson()).thenReturn(persons);

        //When
        List<Person> listPerson = personController.getPerson().getBody();

        //Then
        assertNotNull(listPerson);
        assertTrue(listPerson.contains(personTest));
    }

    @Test
    void when_add_person_return_this_person() {
        //Given
        Person personTest = new Person();
        personTest.setFirstName("David");
        personTest.setLastName("BLANC");

        List<Person> persons = new ArrayList<>();
        persons.add(personTest);

        Mockito.when(personService.getPerson()).thenReturn(persons);

        //When
        List<Person> listPerson = personController.getPerson().getBody();

        //Then
        assertNotNull(listPerson);
        assertTrue(listPerson.contains(personTest));
    }

    @Test
    void when_add_not_person_not_existing() {
        //Given
        Person personExisting = new Person();
        personExisting.setFirstName("David");
        personExisting.setLastName("Blanc");

        Person personNotExisting = new Person();
        personNotExisting.setFirstName("Luc");
        personNotExisting.setLastName("Noir");

        List<Person> persons = new ArrayList<>();

        persons.add(personExisting);
        Mockito.when(personService.getPerson()).thenReturn(persons);

        //When
        List<Person> listPerson = personController.getPerson().getBody();

        //Then
        assertNotNull(listPerson);
        assertTrue(listPerson.contains(personExisting));
        assertThrows(ResponseStatusException.class, () -> personController.addPerson(personNotExisting));
    }

    @Test
    void when_modify_person_return_person_modify() {
        //Given
        Person personModifyTrue = new Person();
        personModifyTrue.setFirstName("Dupont");
        personModifyTrue.setLastName("Jean");

        Mockito.when(personService.modifyPerson("Jean", "Brun", personModifyTrue)).thenReturn(personModifyTrue);

        //When
        personController.modifyPerson("Jean", "Brun", personModifyTrue).getBody();

        //Then
        assertNotNull(personModifyTrue);
        assertEquals("Dupont", personModifyTrue.getFirstName());
        assertEquals("Jean", personModifyTrue.getLastName());

    }

    @Test
    void when_modify_person_return_exception_person_not_existing() {
        //Given
        Person personNotModify = new Person();
        personNotModify.setFirstName("Dupont");
        personNotModify.setLastName("Jean");

        Mockito.when(personService.modifyPerson("Jean", "Brun", personNotModify)).thenReturn(null);

        //When and Then
        assertThrows(ResponseStatusException.class, () -> personController.modifyPerson("Jean", "Brun", personNotModify));

    }

    @Test
    void when_delete_person() {
        //Given
        Person personDeleteExisting = new Person();
        personDeleteExisting.setFirstName("Paul");
        personDeleteExisting.setLastName("Belmondo");

        Mockito.when(personService.deletePerson("Paul", "Belmondo")).thenReturn(personDeleteExisting);

        //When
        personController.deletePerson("Paul", "Belmondo");

        //Then
        assertNotNull(personDeleteExisting);
        assertEquals("Paul", personDeleteExisting.getFirstName());
        assertEquals("Belmondo", personDeleteExisting.getLastName());
    }

    @Test
    void When_delete_person_return_not_existing() {
        //Given
        Person personDeleteNotExisting = new Person();
        personDeleteNotExisting.setFirstName("Julie");
        personDeleteNotExisting.setLastName("Dupont");

        Mockito.when(personService.deletePerson("Pascal", "Dupont")).thenReturn(null);

        //When and Then
        assertThrows(ResponseStatusException.class, () -> personController.deletePerson("Pascal", "Dupont"));
    }
}
