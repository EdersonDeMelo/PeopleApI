package com.ederson.personapi.service;

import com.ederson.personapi.dto.request.PersonDTO;
import com.ederson.personapi.dto.response.MessageResponseDTO;
import com.ederson.personapi.entity.Person;
import com.ederson.personapi.exception.PersonNotFoundException;
import com.ederson.personapi.mapper.PersonMapper;
import com.ederson.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savePerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Criada pessoa com ID: "+ savePerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person>allPeople = personRepository.findAll();
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(()->new PersonNotFoundException(id));

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException(id));
        personRepository.deleteById(id);
    }
}
