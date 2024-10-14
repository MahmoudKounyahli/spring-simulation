package org.apache.maven.archetypes.repo;

import org.apache.maven.archetypes.domain.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class InMemoryRepository implements Repository {
    private static InMemoryRepository instance;
    private final Map<Integer, Person>  map = new HashMap<>();

    public static InMemoryRepository getInstance() {
        if (Objects.isNull(instance)) {
            return new InMemoryRepository();
        }
        return instance;
    }

    private InMemoryRepository() {}

    @Override
    public Optional<Person> findById(int id) {
        return Optional.ofNullable(map.getOrDefault(id, null));
    }

    @Override
    public Optional<Person> findByFirstName(String firstName) {
        return map.values().stream()
                .filter(p -> p.getFirstName().equals(firstName))
                .findFirst();
    }

    @Override
    public Optional<Person> findByLastName(String lastName) {
        return map.values().stream()
                .filter(p -> p.getLastName().equals(lastName))
                .findFirst();
    }

    @Override
    public void save(Person person) {
        if (Objects.isNull(person)) {
            throw new RuntimeException("person object can't be null");
        }
        map.put(person.getId(), person);
    }
}
