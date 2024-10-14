package org.apache.maven.archetypes;

import org.apache.maven.archetypes.domain.Person;
import org.apache.maven.archetypes.exception.PersonNotFoundException;
import org.apache.maven.archetypes.repo.InMemoryRepository;
import org.apache.maven.archetypes.repo.TextFileRepository;
import org.apache.maven.archetypes.service.PersonService;


public class App {
    public static void main(String[] args) {
        // Client.
        var personService = PersonService.getInstance(TextFileRepository.getInstance());
        try {
            personService.getPersonById(1);
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(personService.saveNewPerson(new Person("Micky", "Mouse")));
        System.out.println(personService.getPersonById(0));
    }
}
