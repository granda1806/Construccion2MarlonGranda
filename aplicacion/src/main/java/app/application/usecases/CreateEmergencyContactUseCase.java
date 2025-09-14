package app.application.usecases;

import app.domain.model.EmergencyContact;

public class CreateEmergencyContactUseCase {

    public EmergencyContact execute(int id, String name, String phone) {
        EmergencyContact contact = new EmergencyContact(id, name, phone);
        System.out.println("Contacto de emergencia creado: " + contact);
        return contact;
    }
}
