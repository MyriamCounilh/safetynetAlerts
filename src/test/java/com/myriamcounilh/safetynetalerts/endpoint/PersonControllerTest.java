package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.repository.impl.PersonRepository;
import com.myriamcounilh.safetynetalerts.service.impl.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PersonControllerTest {

    @Test
    void when_get_person_return_person_of_list() {
        //Given
        Person personTest = new Person("Riri", "Blanc");

        //When
        PersonController personController = new PersonController(new PersonService(new PersonRepository()));
        personController.addPerson(personTest);

        //Then
        List<Person> listPerson = personController.getPerson().getBody();
        assertNotNull(listPerson);
        assertTrue(listPerson.contains(personTest));
    }

    @Test
    void when_add_person_return_this_person() {
        //Given
        Person personTest = new Person("Luc", "Blanc");

        //When
        PersonController personController = new PersonController(new PersonService(new PersonRepository()));

        //Then
        assertEquals(personTest, personController.addPerson(personTest).getBody());
    }

    @Test
    void when_add_not_person_not_existing() {
        //Given
        Person personNotTest = new Person("David", "Blanc");

        //When
        PersonController personController = new PersonController(new PersonService(new PersonRepository()));
        personController.addPerson(personNotTest);

        //Then
        assertThrows(ResponseStatusException.class, () -> personController.addPerson(personNotTest));
    }

    @Test
    void when_modify_person_return_person_modify() {
        //Given
        Person personModifyTest = new Person("Jean", "Brun");
        Person personNewNameTest = new Person("Dupont", "Jean");

        //When
        PersonController personController = new PersonController(new PersonService(new PersonRepository()));
        personController.addPerson(personModifyTest);
        personController.modifyPerson("Jean", "Brun", personNewNameTest);

        //Then
        List<Person> listPerson = personController.getPerson().getBody();
        assertNotNull(listPerson);
        assertTrue(listPerson.contains(personNewNameTest));
        assertFalse(listPerson.contains(personModifyTest));
    }

    @Test
    void when_modify_person_return_exception_person_not_existing() {
        //Given
        Person personNotModifyTest = new Person("Jean", "Brun");
        Person personNewNameTest = new Person("Dupont", "Jean");

        //When
        PersonController personController = new PersonController(new PersonService(new PersonRepository()));
        personController.addPerson(personNotModifyTest);
        personController.modifyPerson("Jean", "Brun", personNewNameTest);

        //Then
        assertThrows(ResponseStatusException.class, () -> personController.modifyPerson("Jean", "Brun", personNotModifyTest));
    }


    @Test
    void when_delete_person() {
        //Given
        Person personDeleteTest = new Person("Paul", "Belmondo");

        //When
        PersonController personController = new PersonController(new PersonService(new PersonRepository()));
        personController.addPerson(personDeleteTest);
        personController.deletePerson("Paul", "Belmondo");

        //Then
        assertThrows(ResponseStatusException.class, () -> personController.deletePerson("Paul", "Belmondo"));
    }

    @Test
    void When_delete_person_return_not_existing() {
        //Given
        Person personDeleteTest = new Person("Pascal", "Dupont");

        //When
        PersonController personController = new PersonController(new PersonService(new PersonRepository()));
        personController.addPerson(personDeleteTest);

        //Then
        assertThrows(ResponseStatusException.class, () -> personController.deletePerson("Pascale", "Dupont"));
    }
}
