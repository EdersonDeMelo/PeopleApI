package com.ederson.personapi.service;

import com.ederson.personapi.dto.response.MessageResponseDTO;
import com.ederson.personapi.entity.Person;
import com.ederson.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public MessageResponseDTO createPerson(Person person){
        Person savePerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Criada pessoa com ID: "+ savePerson.getId())
                .build();
    }

}
