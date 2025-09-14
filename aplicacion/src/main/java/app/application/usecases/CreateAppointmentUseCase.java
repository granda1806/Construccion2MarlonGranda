package app.application.usecases;

import app.domain.model.Appointment;

public class CreateAppointmentUseCase {

    public Appointment execute(int id, String description) {
        Appointment appointment = new Appointment(id, description);
        System.out.println("Cita creada: " + appointment);
        return appointment;
    }
}
