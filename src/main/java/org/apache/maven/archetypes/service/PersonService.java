package org.apache.maven.archetypes.service;

import org.apache.maven.archetypes.domain.Person;
import org.apache.maven.archetypes.exception.PersonNotFoundException;
import org.apache.maven.archetypes.repo.Repository;

import java.util.Objects;

public class PersonService {
    private final Repository repository;
    private static PersonService instance;

    public static PersonService getInstance(Repository repository) {
        if (Objects.isNull(instance)) {
            return new PersonService(repository);
        }
        return instance;
    }

    private PersonService(Repository repository) {
        this.repository = repository;
    }

    public Person getPersonById(final int id) {
        var searchedPerson = repository.findById(id);
        if (searchedPerson.isEmpty()) {
            throw new PersonNotFoundException("person with id %s not found".formatted(id));
        }
        return searchedPerson.get();
    }

    public Person saveNewPerson(final Person personToSave) {
        repository.save(personToSave);
        return personToSave;
    }
}
