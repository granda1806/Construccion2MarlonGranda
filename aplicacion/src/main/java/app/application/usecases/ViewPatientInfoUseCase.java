
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
            System.out.println("\n=== Consulta de información del paciente ===");
            System.out.print("Ingrese la cédula del paciente: ");
            String input = reader.nextLine().trim();

            if (input.isEmpty() || !input.matches("\\d+")) {
                System.out.println("⚠️ Debe ingresar un número de cédula válido.");
                return;
            }

            Long document = Long.parseLong(input);

            PatientEntity patient = patientRepository.findByDocument(document);
            if (patient == null) {
                System.out.println("⚠️ Paciente no encontrado en la base de datos.");
                return;
            }

            // 🩺 Información básica del paciente
            System.out.println("\n===== INFORMACIÓN DEL PACIENTE =====");
            System.out.println("🧑 Nombre completo: " + safe(patient.getName()));
            System.out.println("🪪 Documento: " + patient.getDocument());
            System.out.println("🎂 Fecha de nacimiento: " + safe(patient.getBirthDate()));
            System.out.println("⚧ Género: " + safe(patient.getGender()));
            System.out.println("🏠 Dirección: " + safe(patient.getAddress()));
            System.out.println("📞 Teléfono: " + safe(patient.getPhoneNumber()));
            System.out.println("📧 Correo electrónico: " + safe(patient.getEmail()));
            System.out.println("🚨 Contacto de emergencia: " 
                    + safe(patient.getEmergencyContactName())
                    + " (" + safe(patient.getRelationshipPatient()) + "), Tel: "
                    + safe(patient.getEmergencyContactNumber()));
            System.out.println("====================================");

            // 🩺 Mostrar historia clínica si existe
            List<MedicalHistoryEntity> histories = clinicalHistoryRepository.findByPatientDocument(document);
            if (histories == null || histories.isEmpty()) {
                System.out.println("\n📋 No existen historias clínicas registradas para este paciente.");
                return;
            }

            System.out.println("\n===== HISTORIAS CLÍNICAS =====");
            for (MedicalHistoryEntity h : histories) {
                System.out.println("\n📅 Fecha: " + safe(h.getDate()));
                System.out.println("👨‍⚕️ Médico ID: " + (h.getDoctor() != null ? h.getDoctor().getId() : "No registrado"));
                System.out.println("🩻 Motivo: " + safe(h.getReasonForConsultation()));
                System.out.println("🤒 Síntomas: " + safe(h.getSymptoms()));
                System.out.println("🧠 Diagnóstico: " + safe(h.getDiagnosis()));
                System.out.println("📝 Observaciones: " + safe(h.getObservations()));
                System.out.println("------------------------------------");
            }

        } catch (Exception e) {
            System.err.println("❌ Error al consultar la información del paciente: " + e.getMessage());
        }
    }

    // ✅ Método auxiliar para evitar imprimir "null"
    private String safe(Object value) {
        return value == null ? "No registrado" : value.toString();
    }
}
