package app.adapter.in.builder;

import app.adapter.in.validators.UserValidator;
import app.domain.model.Appointment;
import app.domain.model.Patient;
import app.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentBuilder {

    private final UserValidator userValidator;

    @Autowired
    public AppointmentBuilder(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    public Appointment appointmentBuilder(String documentAdmin, String documentPatient) throws Exception {
        User admin = new User();
        Patient patient = new Patient();
        Appointment appointment = new Appointment();

        admin.setDocument(userValidator.documentValidator(documentAdmin));
        patient.setDocument(userValidator.documentValidator(documentPatient));

        appointment.setAdmin(admin);
        appointment.setPatient(patient);
        return appointment;
    }
}
