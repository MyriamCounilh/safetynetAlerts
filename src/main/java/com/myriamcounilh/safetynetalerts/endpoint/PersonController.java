package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * TODO : http://localhost:8080/person
 * Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
 * ● ajouter une nouvelle personne ;
 * ● mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de
 * famille ne changent pas, mais que les autres champs peuvent être modifiés) ;
 * ● supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur
 * unique).
 */

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public List<Person> getPerson() {
        return personService.getPerson();
    }

    /**
     * TODO
     * POST
     * PUT
     * DELETE
     */

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }


}
