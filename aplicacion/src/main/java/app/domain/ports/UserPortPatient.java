package app.domain.ports;

import app.domain.model.Person;

public interface UserPortPatient {
    public Person findByDocument(Person petient) throws Exception;
    public Person findByName(Person petient) throws Exception;
    public void save(Person petient) throws Exception;
}

