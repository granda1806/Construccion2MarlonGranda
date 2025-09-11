package app.domain.ports;

import java.util.*;
import app.domain.model.VitalSignsRecord;

public class Nurses {
    private List<VitalSignsRecord> patients = new ArrayList<>();
    
    public void addVital(VitalSignsRecord patient) {
        patients.add(patient);
    }
    
    public VitalSignsRecord findById(String patientId) {
        for (VitalSignsRecord p : patients) {
            if (p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        return null;
    }
    
    public boolean patientExists(String patientId) {
        return findById(patientId) != null;
    }
}