
package app.application.usecases;

import app.domain.model.Patient;
import app.domain.ports.UserPortPatient;
import app.domain.services.CreatePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientUseCase {

    @Autowired
    private UserPortPatient userPortPatient;

    @Autowired
    private CreatePatient createPatient;

    /**
     * Crear un nuevo paciente
     */
    public Patient create(Patient patient) throws Exception {
        createPatient.create(patient);
        return patient;
    }

    /**
     * Actualizar datos del paciente
     */
    public Patient update(Long id, Patient patient) throws Exception {
        Patient patientToFind = new Patient();
        patientToFind.setId(id);

        // Buscar paciente existente por documento si está disponible
        Patient existing = null;
        if (patient.getDocument() != null && patient.getDocument() > 0) {
            Patient temp = new Patient();
            temp.setDocument(patient.getDocument());
            existing = userPortPatient.findByDocument(temp);
        }

        if (existing == null) {
            throw new Exception("Paciente no encontrado con id: " + id);
        }

        // Actualizar campos permitidos
        if (patient.getNameComplete() != null && !patient.getNameComplete().isEmpty()) {
            existing.setNameComplete(patient.getNameComplete());
        }
        if (patient.getLastnameComplete() != null && !patient.getLastnameComplete().isEmpty()) {
            existing.setLastnameComplete(patient.getLastnameComplete());
        }
        if (patient.getEmail() != null && !patient.getEmail().isEmpty()) {
            existing.setEmail(patient.getEmail());
        }
        if (patient.getPhoneNumber() != null && patient.getPhoneNumber() > 0) {
            existing.setPhoneNumber(patient.getPhoneNumber());
        }
        if (patient.getAddress() != null && !patient.getAddress().isEmpty()) {
            existing.setAddress(patient.getAddress());
        }
        if (patient.getGender() != null && !patient.getGender().isEmpty()) {
            existing.setGender(patient.getGender());
        }
        if (patient.getAge() > 0) {
            existing.setAge(patient.getAge());
        }
        if (patient.getEmergencyContactName() != null && !patient.getEmergencyContactName().isEmpty()) {
            existing.setEmergencyContactName(patient.getEmergencyContactName());
        }
        if (patient.getEmergencyContactNumber() > 0) {
            existing.setEmergencyContactNumber(patient.getEmergencyContactNumber());
        }
        if (patient.getRelationshipPatient() != null && !patient.getRelationshipPatient().isEmpty()) {
            existing.setRelationshipPatient(patient.getRelationshipPatient());
        }

        userPortPatient.save(existing);
        return existing;
    }

    /**
     * Buscar paciente por documento
     */
    public Patient findByDocument(Long document) throws Exception {
        Patient patient = new Patient();
        patient.setDocument(document);
        Patient found = userPortPatient.findByDocument(patient);
        if (found == null) {
            throw new Exception("Paciente no encontrado con documento: " + document);
        }
        return found;
    }

    /**
     * Buscar paciente por nombre
     */
    public Patient findByName(String name) throws Exception {
        Patient patient = new Patient();
        patient.setNameComplete(name);
        Patient found = userPortPatient.findByName(patient);
        if (found == null) {
            throw new Exception("Paciente no encontrado con nombre: " + name);
        }
        return found;
    }
}
