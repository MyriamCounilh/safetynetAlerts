package com.myriamcounilh.safetynetalerts.service.impl;

import com.myriamcounilh.safetynetalerts.model.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class PersonServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void addPerson() {
    }

    @Test
    public void getPerson() throws Exception {

        Person person = new Person();
        person.setFirstName("Thomas");
        person.setLastName("BLANC");

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        Mockito.when(personService.getPerson()).thenReturn(persons);

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName").value("Thomas"))
                .andExpect(jsonPath("$.[0].lastName").value("BLANC"));
    }

    @Test
    void modifyPerson() {
    }

    @Test
    void deletePerson() {
    }
}
