package app.domain.ports;

import app.domain.model.Appointment;

public interface AppointmentPort {
    
    public Appointment findById(Appointment createAppoinment) throws Exception;
    public void save(Appointment createAppoinment) throws Exception;
}
