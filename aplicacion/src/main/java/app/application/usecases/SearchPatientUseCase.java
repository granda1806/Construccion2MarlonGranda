package app.application.usecases;

import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.repository.PatientRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class SearchPatientUseCase {

    private final Scanner reader = new Scanner(System.in);
    private final PatientRepository patientRepository;

    public SearchPatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void execute() {
        System.out.print("\nIngrese la cedula del paciente: ");
        Long document = Long.valueOf(reader.nextLine());

        Optional<PatientEntity> patientOpt = patientRepository.findByDocument(document);

        if (patientOpt.isEmpty()) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        PatientEntity patient = patientOpt.get();

        System.out.println("\n===== INFORMACIÓN DEL PACIENTE =====");
        System.out.println("Nombre: " + patient.getName());
        System.out.println("Documento: " + patient.getDocument());
        System.out.println("Género: " + patient.getGender());
        System.out.println("Dirección: " + patient.getAddress());
        System.out.println("Teléfono: " + patient.getPhoneNumber());
        System.out.println("Correo: " + patient.getEmail());
        System.out.println("Contacto de emergencia: "
                + patient.getEmergencyContactName()
                + " (" + patient.getRelationshipPatient() + "), Tel: "
                + patient.getEmergencyContactNumber());
        System.out.println("====================================");
    }
}
