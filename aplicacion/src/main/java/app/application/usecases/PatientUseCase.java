
package app.application.usecases;

import app.domain.model.Patient;
import java.util.HashMap;
import java.util.Map;

public class PatientUseCase {
    private final Map<Long, Patient> patients = new HashMap<>();

    
    public Patient create(Patient patient) {
        patients.put(patient.getId(), patient);
        return patient;
    }

    
    public Patient update(int id, Patient patient) {
        Patient existing = patients.get(id);
        if (existing == null) {
            throw new RuntimeException("Paciente no encontrado con id: " + id);
        }
       // existing.updateFrom(patient);
        return existing;
    }

   
    public Patient findById(int id) {
        return patients.get(id);
    }
}
