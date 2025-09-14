package app.application.usecases;

import app.domain.model.Patient;

public class CreatePatientUseCase {

    public Patient execute(int id, String name) {
        Patient patient = new Patient(id, name);
        System.out.println("Paciente creado: " + patient);
        return patient;
    }
}
