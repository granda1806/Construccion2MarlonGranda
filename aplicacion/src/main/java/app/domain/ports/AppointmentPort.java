package app.domain.ports;

import app.domain.model.Appointment;
import app.domain.model.Patient;
import java.util.List;


public interface AppointmentPort {
    
    public Appointment findById(Appointment createAppoinment) throws Exception;
    public List<Appointment> findByDocumentPatient(Patient patient)throws Exception;
    public void save(Appointment createAppoinment) throws Exception;
}
