package com.myriamcounilh.safetynetalerts.endpoint;

import com.myriamcounilh.safetynetalerts.model.Person;
import com.myriamcounilh.safetynetalerts.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LogManager.getLogger("PersonController");

    private final IPersonService personService;

    @Autowired
    public PersonController (IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPerson() {
        logger.info("Get Mapping it OK");
        return new ResponseEntity<>(personService.getPerson(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person) {
        Person personCreated = personService.addPerson(person);
        if (personCreated != null ) {
            logger.info("Post Mapping it OK");
            return new ResponseEntity<>(personCreated, HttpStatus.OK);
        } else {
            logger.error("Post Mapping is error {}", person );
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Person existing");
        }
    }

    @PutMapping
    public ResponseEntity<Person> modifyPerson(@RequestParam String firstName, String lastName, @Valid @RequestBody Person person) {
        Person personModify = personService.modifyPerson(firstName, lastName, person);
        if (personModify != null) {
            logger.info("Put Mapping for modify Person OK");
            return new ResponseEntity<>(personModify, HttpStatus.OK);
        } else {
            logger.error("Put Mapping is error {}", person );
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not existing");
        }
    }

    @DeleteMapping
    public void deletePerson(@RequestParam String firstName, String lastName) {
        if (personService.deletePerson(firstName, lastName) != null) {
            logger.info("Delete Mapping for supprime Person OK");
        } else {
            logger.error("Delete Mapping is error {} - {}", firstName, lastName );
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
    }

}
