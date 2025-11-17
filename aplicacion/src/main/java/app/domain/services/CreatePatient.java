package app.domain.services;

import app.domain.model.Patient;
import app.domain.ports.UserPortPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePatient {
    @Autowired
    private UserPortPatient userPortPatient;

    public void create (Patient patient) throws Exception {
        Patient existingPatient = userPortPatient.findByDocument(patient);
        
        if (existingPatient != null) {
            throw new Exception("Ya hay un paciente registrado con este documento");
        }
        
        userPortPatient.save(patient);
    }
}
