package app.application.usecases;

import app.domain.model.Person;
import app.domain.model.enums.Role;
import app.domain.services.CreatePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUseCase
{
    @Autowired
    private CreatePatient createPatient;
    
    public void createPatient(Person patient) throws Exception
    {
        createPatient.create(patient); 
    }
    
}
