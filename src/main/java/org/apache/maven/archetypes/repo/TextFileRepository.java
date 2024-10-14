package org.apache.maven.archetypes.repo;

import org.apache.maven.archetypes.config.ConfigLoader;
import org.apache.maven.archetypes.domain.Person;

import java.io.*;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class TextFileRepository implements Repository {
    private final ConfigLoader configLoader = ConfigLoader.getInstance();
    private final String databaseFileUrl = configLoader.getDatabaseFileUrl();
    private final File file = new File(databaseFileUrl);

    private final PrintWriter printWriter;

    private static TextFileRepository instance;

    public TextFileRepository() {
        try {
            printWriter = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static TextFileRepository getInstance() {
        if (Objects.isNull(instance)) {
            return new TextFileRepository();
        }
        return instance;
    }

    @Override
    public Optional<Person> findById(int id) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        while (scanner.hasNext()) {
            var line = scanner.nextLine();
            if (line.contains(String.valueOf(id))) {
                return Optional.of(stringToPerson(line));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> findByFirstName(String firstName) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> findByLastName(String lastName) {
        return Optional.empty();
    }

    @Override
    public void save(Person person) {
        printWriter.append(String.valueOf(person.getId()))
                .append(" ").append(person.getFirstName())
                .append(" ").append(person.getLastName())
                .append("\n");
        printWriter.close();
    }

    private Person stringToPerson(final String line) {
        if (line.isEmpty()) {
            throw new RuntimeException("record is empty");
        }
        var recordList = line.split("\\s+");
        return new Person(recordList[1], recordList[2]);
    }
}
