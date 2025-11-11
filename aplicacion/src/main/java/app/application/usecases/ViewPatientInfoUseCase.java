package app.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Scanner;

import app.infrastructure.persistence.repository.PatientRepository;
import app.infrastructure.persistence.repository.ClinicalHistoryRepository;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;

@Service
public class ViewPatientInfoUseCase {

    private final Scanner reader = new Scanner(System.in);
    private final PatientRepository patientRepository;
    private final ClinicalHistoryRepository clinicalHistoryRepository;

    @Autowired
    public ViewPatientInfoUseCase(PatientRepository patientRepository,
                                  ClinicalHistoryRepository clinicalHistoryRepository) {
        this.patientRepository = patientRepository;
        this.clinicalHistoryRepository = clinicalHistoryRepository;
    }

    public void execute() {
        try {
            System.out.println("\n=== Consulta de paciente ===");
            System.out.print("Ingrese la cédula del paciente: ");
            Long document = reader.nextLong();

            PatientEntity patient = patientRepository.findByDocument(document);
            if (patient == null) {
                System.out.println("⚠️ Paciente no encontrado.");
                return;
            }

            System.out.println("\n===== INFORMACIÓN DEL PACIENTE =====");
            System.out.println("Nombre: " + patient.getName());
            System.out.println("Documento: " + patient.getDocument());
            System.out.println("Fecha de nacimiento: " + patient.getBirthDate());
            System.out.println("Género: " + patient.getGender());
            System.out.println("Dirección: " + patient.getAddress());
            System.out.println("Teléfono: " + patient.getPhoneNumber());
            System.out.println("Correo: " + patient.getEmail());
            System.out.println("Contacto de emergencia: " + patient.getEmergencyContactName()
                    + " (" + patient.getRelationshipPatient() + "), Tel: " + patient.getEmergencyContactNumber());
            System.out.println("====================================");

            List<MedicalHistoryEntity> histories = clinicalHistoryRepository.findByPatientDocument(document);
            if (histories.isEmpty()) {
                System.out.println("No existen historias clínicas registradas para este paciente.");
                return;
            }

            System.out.println("\n===== HISTORIAS CLÍNICAS =====");
            for (MedicalHistoryEntity h : histories) {
                System.out.println("\nFecha: " + h.getDate());
                System.out.println("Médico ID: " + (h.getDoctor() != null ? h.getDoctor().getId() : "No registrado"));
                System.out.println("Motivo: " + h.getReasonForConsultation());
                System.out.println("Síntomas: " + h.getSymptoms());
                System.out.println("Diagnóstico: " + h.getDiagnosis());
                System.out.println("Observaciones: " + h.getObservations());
            }
        } catch (Exception e) {
            System.err.println("❌ Error consultando la información del paciente: " + e.getMessage());
        }
    }
}
