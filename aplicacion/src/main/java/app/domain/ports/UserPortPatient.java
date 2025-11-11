
package app.domain.ports;

import app.domain.model.Patient;

public interface UserPortPatient {
    public Patient findByDocument(Patient petient) throws Exception;
    public Patient findByName(Patient petient) throws Exception;
    public void save(Patient petient) throws Exception;
}

