package com.ederson.personapi.controller;


import com.ederson.personapi.dto.response.MessageResponseDTO;
import com.ederson.personapi.entity.Person;
import com.ederson.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

   @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person){
       Person savePerson = personRepository.save(person);
       return MessageResponseDTO
               .builder()
               .message("Criada pessoa com ID"+ savePerson.getId())
               .build();
   }
}
