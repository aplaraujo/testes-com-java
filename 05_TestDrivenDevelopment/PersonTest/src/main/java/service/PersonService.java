package service;

import entities.Person;

public class PersonService implements IPersonService {

    @Override
    public Person createPerson(Person person) {

        return new Person();
    }
}
