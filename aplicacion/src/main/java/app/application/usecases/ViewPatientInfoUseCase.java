package app.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Scanner;

import app.infrastructure.persistence.repository.PatientRepository;
import app.infrastructure.persistence.repository.MedicalHistoryRepository;
import app.infrastructure.persistence.repository.MedicalOrderRepository;
import app.infrastructure.persistence.repository.PrescriptionRepository;
import app.infrastructure.persistence.repository.ProcedureRepository;
import app.infrastructure.persistence.repository.DiagnosticTestRepository;
import app.infrastructure.persistence.repository.VitalSignsRepository;
import java.util.Optional;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.entities.PrescriptionEntity;
import app.infrastructure.persistence.entities.ProcedureEntity;
import app.infrastructure.persistence.entities.DiagnosticTestEntity;
import app.infrastructure.persistence.entities.VitalSignsEntity;

@Service
public class ViewPatientInfoUseCase {

    private final Scanner reader = new Scanner(System.in);
    private final PatientRepository patientRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MedicalOrderRepository medicalOrderRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final ProcedureRepository procedureRepository;
    private final DiagnosticTestRepository diagnosticTestRepository;
    private final VitalSignsRepository vitalSignsRepository;

    @Autowired
    public ViewPatientInfoUseCase(
            PatientRepository patientRepository,
            MedicalHistoryRepository medicalHistoryRepository,
            MedicalOrderRepository medicalOrderRepository,
            PrescriptionRepository prescriptionRepository,
            ProcedureRepository procedureRepository,
            DiagnosticTestRepository diagnosticTestRepository,
            VitalSignsRepository vitalSignsRepository) {
        this.patientRepository = patientRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.medicalOrderRepository = medicalOrderRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.procedureRepository = procedureRepository;
        this.diagnosticTestRepository = diagnosticTestRepository;
        this.vitalSignsRepository = vitalSignsRepository;
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

            Optional<PatientEntity> patientOpt = patientRepository.findByDocument(document);
            if (patientOpt.isEmpty()) {
                System.out.println("⚠️ Paciente no encontrado en la base de datos.");
                return;
            }

            PatientEntity patient = patientOpt.get();

            // 🩺 Información básica del paciente
            System.out.println("\n═════════════════════════════════════════════════════");
            System.out.println("           INFORMACIÓN DEL PACIENTE");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.println("Nombre completo: " + safe(patient.getName()));
            System.out.println("Documento: " + patient.getDocument());
            System.out.println("Género: " + safe(patient.getGender()));
            System.out.println("Dirección: " + safe(patient.getAddress()));
            System.out.println("Teléfono: " + safe(patient.getPhoneNumber()));
            System.out.println("Correo electrónico: " + safe(patient.getEmail()));
            System.out.println("Contacto de emergencia: " + safe(patient.getEmergencyContactName())
                    + " (" + safe(patient.getRelationshipPatient()) + "), Tel: "
                    + safe(patient.getEmergencyContactNumber()));
            System.out.println("═════════════════════════════════════════════════════");

            // 📋 Mostrar historias clínicas
            List<MedicalHistoryEntity> histories = medicalHistoryRepository.findByPatientDocument(document);
            if (histories != null && !histories.isEmpty()) {
                System.out.println("\n📋 HISTORIAS CLÍNICAS (" + histories.size() + ")");
                System.out.println("─────────────────────────────────────────────────────");

                for (int i = 0; i < histories.size(); i++) {
                    MedicalHistoryEntity h = histories.get(i);
                    System.out.println("\n[Historia " + (i + 1) + "]");
                    System.out.println("📅 Fecha: " + safe(h.getDate()));

                    // Manejo seguro del doctor
                    String doctorName = "No registrado";
                    try {
                        if (h.getDoctor() != null && h.getDoctor().getName() != null) {
                            doctorName = h.getDoctor().getName();
                        }
                    } catch (Exception e) {
                        doctorName = "Error al cargar doctor";
                    }
                    System.out.println("👨‍⚕️ Médico: " + doctorName);

                    System.out.println("🩻 Motivo de consulta: " + safe(h.getReasonForConsultation()));
                    System.out.println("🤒 Síntomas: " + safe(h.getSymptoms()));
                    System.out.println("🧠 Diagnóstico: " + safe(h.getDiagnosis()));
                    System.out.println("📝 Observaciones: " + safe(h.getObservations()));
                }
            } else {
                System.out.println("\n⚠️ No hay historias clínicas registradas.");
            }

            // 💊 Mostrar órdenes médicas
            List<MedicalOrderEntity> orders = medicalOrderRepository.findByPatientDocument(document);
            if (orders != null && !orders.isEmpty()) {
                System.out.println("\n\n📋 ÓRDENES MÉDICAS (" + orders.size() + ")");
                System.out.println("─────────────────────────────────────────────────────");

                for (int i = 0; i < orders.size(); i++) {
                    MedicalOrderEntity order = orders.get(i);
                    System.out.println("\n[Orden " + (i + 1) + "]");
                    System.out.println("🏥 Número de orden: " + safe(order.getOrderNumber()));
                    System.out.println("📅 Fecha de creación: " + safe(order.getCreatedAt()));

                    // Manejo seguro del doctor
                    String doctorName = "No registrado";
                    try {
                        if (order.getDoctor() != null && order.getDoctor().getName() != null) {
                            doctorName = order.getDoctor().getName();
                        }
                    } catch (Exception e) {
                        doctorName = "Error al cargar doctor";
                    }
                    System.out.println("👨‍⚕️ Doctor: " + doctorName);

                    System.out.println("📝 Observaciones: " + safe(order.getObservations()));

                    // 💊 Mostrar prescripciones de esta orden
                    List<PrescriptionEntity> prescriptions = prescriptionRepository
                            .findByMedicalOrder_Id(order.getId());
                    if (prescriptions != null && !prescriptions.isEmpty()) {
                        System.out.println("\n  💊 MEDICAMENTOS PRESCRITOS (" + prescriptions.size() + "):");
                        for (PrescriptionEntity p : prescriptions) {
                            System.out.println("    • Medicamento: " + safe(p.getMedicineId()));
                            System.out.println("      - Dosis: " + safe(p.getDose()));
                            System.out.println("      - Duración: " + safe(p.getDuration()));
                            System.out.println("      - Ítem: " + p.getItem());
                        }
                    }

                    // 🏥 Mostrar procedimientos de esta orden
                    List<ProcedureEntity> procedures = procedureRepository.findByMedicalOrder_Id(order.getId());
                    if (procedures != null && !procedures.isEmpty()) {
                        System.out.println("\n  🏥 PROCEDIMIENTOS (" + procedures.size() + "):");
                        for (ProcedureEntity pr : procedures) {
                            System.out.println("    • Procedimiento: " + safe(pr.getProcedureId()));
                            System.out.println("      - Cantidad: " + pr.getQuantity());
                            System.out.println("      - Frecuencia: " + safe(pr.getFrequency()));
                            System.out.println("      - Costo: $" + pr.getCost());
                            System.out.println(
                                    "      - ¿Requiere especialista?: " + (pr.isRequiresSpecialist() ? "Sí" : "No"));
                            if (pr.isRequiresSpecialist()) {
                                System.out.println("      - Especialista: " + safe(pr.getSpecialistTypeId()));
                            }
                        }
                    }

                    // 🔬 Mostrar pruebas diagnósticas de esta orden
                    List<DiagnosticTestEntity> tests = diagnosticTestRepository.findByMedicalOrder_Id(order.getId());
                    if (tests != null && !tests.isEmpty()) {
                        System.out.println("\n  🔬 PRUEBAS DIAGNÓSTICAS (" + tests.size() + "):");
                        for (DiagnosticTestEntity t : tests) {
                            System.out.println("    • Prueba: " + safe(t.getTestId()));
                            System.out.println("      - Ítem: " + t.getItem());
                        }
                    }

                    // 🩺 Mostrar signos vitales de esta orden (registrados por enfermería)
                    try {
                        java.util.List<VitalSignsEntity> vitals = vitalSignsRepository
                                .findByMedicalOrder_Id(order.getId());
                        if (vitals != null && !vitals.isEmpty()) {
                            System.out.println("\n  🩺 SIGNOS VITALES (" + vitals.size() + "):");
                            for (VitalSignsEntity v : vitals) {
                                System.out.println("    • Presión arterial: " + safe(v.getBloodPressure()));
                                System.out.println("      - Temperatura: " + v.getTemperature());
                                System.out.println("      - Pulso: " + v.getPulse());
                                System.out.println("      - Nivel de oxígeno: " + v.getBloodOxygenLevel());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("\n  ⚠️ No se pudieron cargar los signos vitales: " + e.getMessage());
                    }

                    System.out.println("\n  ─────────────────────────────────────");
                }
            } else {
                System.out.println("\n⚠️ No hay órdenes médicas registradas.");
            }

            System.out.println("\n═════════════════════════════════════════════════════");
            System.out.println("✅ Fin de consulta.");
            System.out.println("═════════════════════════════════════════════════════");

        } catch (Exception e) {
            System.err.println("❌ Error al consultar la información del paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ✅ Método auxiliar para evitar imprimir "null"
    private String safe(Object value) {
        return value == null ? "No registrado" : value.toString();
    }
}
