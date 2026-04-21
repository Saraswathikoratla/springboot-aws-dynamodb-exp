package com.saraswathi.controller;

import com.saraswathi.entity.Person;
import com.saraswathi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/save/person")
    public Person savePerson(@RequestBody Person person){
        return personRepository.addPerson(person);
    }

    @GetMapping("/getperson/{personId}")
    public Person getPerson(@PathVariable String personId){
        return personRepository.getPerson(personId);
    }


    @DeleteMapping("/delete/person")
    public String detletePerson(@RequestBody Person person){
        return personRepository.deletPerson(person);
    }





    @PostMapping("/edit/person")
    public String updatePerson(@RequestBody Person person){
       return personRepository.editPerson(person);
    }
}
