package app.application.usecases;

import app.domain.model.Patient;

public class UpdatePatientUseCase
{

    public void execute(Patient patient, String newName)
    {
        
        patient.setName(newName);
        System.out.println("Paciente actualizado: " + patient);
        
    }
    
}
