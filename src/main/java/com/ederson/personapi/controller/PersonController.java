package com.ederson.personapi.controller;


import com.ederson.personapi.dto.response.MessageResponseDTO;
import com.ederson.personapi.entity.Person;
import com.ederson.personapi.repository.PersonRepository;
import com.ederson.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PersonController {


  private PersonService personService;

  @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
      return personService.createPerson(person);
    }
}
