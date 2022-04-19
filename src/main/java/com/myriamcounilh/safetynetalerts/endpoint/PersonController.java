package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



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

    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

}
