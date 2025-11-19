package app.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.infrastructure.persistence.repository.ClinicalHistoryRepository;
import app.infrastructure.persistence.repository.DoctorRepository;
import app.infrastructure.persistence.entities.DoctorEntity;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.mapper.DoctorMapper;
import app.domain.model.ClinicalHistoryRecord;
import app.domain.model.Prescription;
import app.domain.model.Procedure;

@Service
public class RegisterClinicalHistoryUseCase {

    private final Scanner reader = new Scanner(System.in);
    private final ClinicalHistoryRepository clinicalHistoryRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public RegisterClinicalHistoryUseCase(ClinicalHistoryRepository clinicalHistoryRepository,
            DoctorRepository doctorRepository) {
        this.clinicalHistoryRepository = clinicalHistoryRepository;
        this.doctorRepository = doctorRepository;
    }

    public void execute() {
        try {
            System.out.println("\n=== Registro de nueva historia clínica ===");

            System.out.print("Ingrese la cédula del paciente: ");
            String patientInput = reader.nextLine().trim();
            Long patientDocument;
            try {
                patientDocument = Long.parseLong(patientInput);
            } catch (NumberFormatException nfe) {
                System.out.println("⚠️ Cédula de paciente inválida. Operación cancelada.");
                return;
            }

            System.out.print("Ingrese la cédula del médico: ");
            String doctorInput = reader.nextLine().trim();
            Long doctorId;
            try {
                doctorId = Long.parseLong(doctorInput);
            } catch (NumberFormatException nfe) {
                System.out.println("⚠️ Cédula de médico inválida. Operación cancelada.");
                return;
            }

            ClinicalHistoryRecord record = new ClinicalHistoryRecord();
            record.setDate(LocalDate.now());
            record.setDoctorId(doctorId);
            record.setPatientDocument(patientDocument);

            System.out.print("Motivo de consulta: ");
            record.setReasonForConsultation(reader.nextLine());

            System.out.print("Síntomas: ");
            record.setSymptoms(reader.nextLine());

            System.out.print("Diagnóstico: ");
            record.setDiagnosis(reader.nextLine());

            // Prescripciones
            List<Prescription> prescriptions = new ArrayList<>();
            System.out.print("¿Desea registrar medicamentos? (s/n): ");
            if (reader.nextLine().equalsIgnoreCase("s")) {
                boolean adding = true;
                while (adding) {
                    System.out.print("Número de orden: ");
                    String orderNumber = reader.nextLine();
                    System.out.print("ID del medicamento: ");
                    String medId = reader.nextLine();
                    System.out.print("Dosis: ");
                    String dose = reader.nextLine();
                    System.out.print("Duración: ");
                    String duration = reader.nextLine();
                    System.out.print("Ítem (número): ");
                    int item = Integer.parseInt(reader.nextLine());

                    prescriptions.add(new Prescription(orderNumber, medId, dose, duration, item));

                    System.out.print("¿Agregar otro medicamento? (s/n): ");
                    adding = reader.nextLine().equalsIgnoreCase("s");
                }
            }
            record.setPrescriptions(prescriptions);

            // Procedimientos
            List<Procedure> procedures = new ArrayList<>();
            System.out.print("¿Desea registrar procedimientos? (s/n): ");
            if (reader.nextLine().equalsIgnoreCase("s")) {
                boolean adding = true;
                while (adding) {
                    System.out.print("Número de orden: ");
                    String orderNumber = reader.nextLine();
                    System.out.print("ID procedimiento: ");
                    String procId = reader.nextLine();
                    System.out.print("Cantidad: ");
                    int quantity = Integer.parseInt(reader.nextLine());
                    System.out.print("Frecuencia: ");
                    String frequency = reader.nextLine();
                    System.out.print("Costo: ");
                    double cost = Double.parseDouble(reader.nextLine());
                    System.out.print("¿Requiere especialista? (true/false): ");
                    boolean requiresSpec = Boolean.parseBoolean(reader.nextLine());
                    System.out.print("Tipo especialista (opcional): ");
                    String specType = reader.nextLine();

                    int item = procedures.size() + 1;
                    procedures.add(new Procedure(orderNumber, procId, quantity, frequency, cost, requiresSpec, specType,
                            item));

                    System.out.print("¿Agregar otro procedimiento? (s/n): ");
                    adding = reader.nextLine().equalsIgnoreCase("s");
                }
            }
            record.setProcedures(procedures);

            // Guardar en MySQL
            MedicalHistoryEntity entity = DoctorMapper.toEntity(record);

            // Resolver doctor por cédula (document). Si no existe, crearlo automáticamente.
            if (record.getDoctorId() != null) {
                Long docId = record.getDoctorId();
                java.util.Optional<DoctorEntity> maybeDoc = doctorRepository.findByDocument(docId);
                DoctorEntity doctorEntity;
                if (maybeDoc.isPresent()) {
                    doctorEntity = maybeDoc.get();
                } else {
                    // No crear automáticamente: permitir ingresar los datos del médico
                    System.out.println("⚠️ No se encontró médico con cédula: " + docId + ".");
                    System.out.print("¿Desea crear un médico con esa cédula? (s/n): ");
                    String createChoice = reader.nextLine();
                    if (createChoice.equalsIgnoreCase("s")) {
                        System.out.print("Nombre del médico: ");
                        String name = reader.nextLine();
                        System.out.print("Especialidad: ");
                        String specialty = reader.nextLine();
                        System.out.print("Número de licencia: ");
                        String license = reader.nextLine();
                        System.out.print("Email: ");
                        String email = reader.nextLine();

                        doctorEntity = new DoctorEntity();
                        doctorEntity.setDocumentNumber(docId);
                        doctorEntity.setName(name == null || name.isEmpty() ? "Médico no registrado" : name);
                        doctorEntity.setSpecialty(specialty == null || specialty.isEmpty() ? "No definida" : specialty);
                        doctorEntity.setLicenseNumber(license == null || license.isEmpty() ? "N/A" : license);
                        doctorEntity.setEmail(email == null || email.isEmpty() ? "no-reply@hospital.local" : email);
                        doctorEntity = doctorRepository.save(doctorEntity);
                        System.out.println("ℹ️ Médico creado con cédula: " + docId);
                    } else {
                        System.err.println("❌ No se guardó la historia porque no hay médico asociado.");
                        return;
                    }
                }
                entity.setDoctor(doctorEntity);
            } else {
                System.err.println("❌ No se proporcionó cédula del médico. No se guardó la historia.");
                return;
            }

            clinicalHistoryRepository.save(entity);

            System.out.println("\n✅ Historia clínica registrada correctamente en MySQL.");
        } catch (Exception e) {
            System.err.println("❌ Error al registrar la historia clínica: " + e.getMessage());
        }
    }
}
