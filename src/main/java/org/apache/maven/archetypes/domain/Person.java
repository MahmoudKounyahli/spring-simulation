package org.apache.maven.archetypes.domain;

public class Person {
    private final Integer id;
    private final String firstName;
    private final String lastName;

    private static int counter = 0;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Person(final String firstName, final String lastName) {
        this(counter, firstName, lastName);
        counter++;
    }

    private Person(final int id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
