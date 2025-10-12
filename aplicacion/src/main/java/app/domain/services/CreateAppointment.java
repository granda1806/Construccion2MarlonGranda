
package app.domain.services;

import app.domain.model.Appointment;
import app.domain.model.Patient;
import app.domain.model.User;
import app.domain.ports.UserPortOut;
import app.domain.ports.UserPortPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.model.enums.Role;
import java.sql.Date;
import app.domain.ports.AppointmentPort;

@Service
public class CreateAppointment {
    @Autowired
    private UserPortOut userPort;
    @Autowired
    private UserPortPatient portPatient;
    @Autowired
    private AppointmentPort apointmentPort;
    
    public void create(Appointment appointment) throws Exception {
        User admin = userPort.findByDocument(appointment.getAdmin());
        if (admin == null || !admin.getRole().equals(Role.ADMIN)) {
            throw new Exception("El administrador no existe o el usuario no es un administrador.");
        }
        Patient patient = portPatient.findByDocument(appointment.getPatient());
        if (patient == null) {
            throw new Exception("El paciente no existe...");
        }
        appointment.setDate(new Date(System.currentTimeMillis()));
        appointment.setPatient(patient);
        appointment.setAdmin(admin);
        
        apointmentPort.save(appointment);
    }
  
}
