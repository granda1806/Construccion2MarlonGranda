
package app.application.usecases;

import app.domain.model.Appointment;
import app.domain.model.Patient;
import app.domain.services.CreateAppointment;
import app.domain.services.CreatePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUseCase
{
    @Autowired
    private CreatePatient createPatient;
    
    @Autowired CreateAppointment createAppointment;
    
    public void createPatient(Patient patient) throws Exception
    {
        createPatient.create(patient); 
    }
    
    public void createAppointment(Appointment appointment) throws Exception
    {
        createAppointment.create(appointment);
    }
    
}
