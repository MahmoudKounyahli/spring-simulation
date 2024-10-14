package org.apache.maven.archetypes.repo;

import org.apache.maven.archetypes.domain.Person;

import java.util.Optional;

public interface Repository {
    Optional<Person> findById(final int id);
    Optional<Person> findByFirstName(final String firstName);
    Optional<Person> findByLastName(final String lastName);
    void save(final Person person);
}
