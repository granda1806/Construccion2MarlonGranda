package app.application.usecases;

import app.domain.model.DiagnosticAid;
import app.domain.model.MedicalOrder;
import app.domain.model.Prescription;
import app.domain.model.Procedure;
import app.infrastructure.persistence.entities.DoctorEntity;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.repository.DoctorRepository;
import app.infrastructure.persistence.repository.MedicalHistoryRepository;
import app.infrastructure.persistence.repository.MedicalOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DoctorUseCase {

    private final Scanner reader = new Scanner(System.in);

    @Autowired
    private MedicalHistoryRepository historyRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalOrderRepository medicalOrderRepository;

    // Orden médica activa en la sesión del médico
    private MedicalOrder currentOrder;

    // ================== MENÚ PRINCIPAL ==================
    public void manageMedicalHistory() {
        System.out.print("\nIngrese el ID del paciente: ");
        Long patientId;

        try {
            patientId = Long.parseLong(reader.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ El ID del paciente debe ser numérico.");
            return;
        }

        boolean exists = historyRepository.existsByPatientId(patientId);
        if (!exists) {
            System.out.println("❌ No se encontró historia clínica para este paciente.");
            return;
        }

        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n===== SUBMENÚ HISTORIA CLÍNICA =====");
            System.out.println("1. Agregar información de la consulta");
            System.out.println("2. Recetar medicamentos");
            System.out.println("3. Procedimiento médico");
            System.out.println("4. Ayuda diagnóstica");
            System.out.println("5. Crear orden médica");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String option = reader.nextLine().trim();

            switch (option) {
                case "1" -> consultationInformation(patientId);
                case "2" -> prescribeMedicine();
                case "3" -> medicalProcedure();
                case "4" -> diagnosticAssistance();
                case "5" -> createMedicalOrder();
                case "6" -> {
                    System.out.println("↩️ Volviendo al menú principal...");
                    inMenu = false;
                }
                default -> System.out.println("⚠️ Opción no válida.");
            }
        }
    }

    // ================== MÉTODOS ==================

    private void consultationInformation(Long patientId) {
        System.out.println("\n🩺 AGREGAR INFORMACIÓN DE LA CONSULTA");

        System.out.print("Fecha (YYYY-MM-DD): ");
        LocalDate date;
        try {
            date = LocalDate.parse(reader.nextLine().trim());
        } catch (Exception e) {
            System.out.println("⚠️ Formato de fecha inválido. Use YYYY-MM-DD.");
            return;
        }

        System.out.print("Cédula del médico: ");
        Long doctorDocument;
        try {
            doctorDocument = Long.parseLong(reader.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ La cédula del médico debe ser numérica.");
            return;
        }

        Optional<DoctorEntity> doctorOpt = doctorRepository.findByDocument(doctorDocument);
        if (doctorOpt.isEmpty()) {
            System.out.println("❌ No existe un médico registrado con cédula " + doctorDocument);
            return;
        }

        System.out.print("Motivo de consulta: ");
        String reason = reader.nextLine();

        System.out.print("Sintomatología: ");
        String symptoms = reader.nextLine();

        System.out.print("Diagnóstico: ");
        String diagnosis = reader.nextLine();

        MedicalHistoryEntity entity = new MedicalHistoryEntity();
        entity.setDoctor(doctorOpt.get());
        entity.setPatientId(patientId);
        entity.setDate(java.sql.Date.valueOf(date));
        entity.setReasonForConsultation(reason);
        entity.setSymptoms(symptoms);
        entity.setDiagnosis(diagnosis);
        entity.setObservations("Registro ingresado por el doctor");

        try {
            historyRepository.save(entity);
            System.out.println("✅ Historia clínica guardada correctamente.");
        } catch (Exception e) {
            System.out.println("⚠️ Error al guardar la historia clínica: " + e.getMessage());
        }
    }

    private void prescribeMedicine() {
        if (currentOrder == null) {
            System.out.println("\n⚠️ No hay una orden médica activa. Cree una primero.");
            return;
        }

        Prescription prescription = new Prescription();

        System.out.print("Ingrese ID del medicamento: ");
        prescription.setMedicineId(reader.nextLine());

        System.out.print("Ingrese dosis: ");
        prescription.setDose(reader.nextLine());

        System.out.print("Ingrese duración del tratamiento: ");
        prescription.setDuration(reader.nextLine());

        System.out.print("Ingrese número de ítem: ");
        int item = reader.nextInt();

        if (currentOrder.containsItem(item)) {
            System.out.println("⚠️ Ya existe un elemento con ese número de ítem en esta orden.");
            return;
        }

        prescription.setItem(item);
        currentOrder.addPrescription(prescription);
        System.out.println("✅ Medicamento recetado correctamente.");
    }

    private void medicalProcedure() {
        if (currentOrder == null) {
            currentOrder = new MedicalOrder();
            System.out.print("\nIngrese número de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine());
        }

        Procedure procedure = new Procedure();

        System.out.print("ID del procedimiento: ");
        procedure.setProcedureId(reader.nextLine());

        System.out.print("Cantidad: ");
        procedure.setQuantity(reader.nextInt());
        reader.nextLine(); // limpiar buffer

        System.out.print("Frecuencia: ");
        procedure.setFrequency(reader.nextLine());

        System.out.print("¿Requiere especialista? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            System.out.print("ID del especialista: ");
            procedure.setSpecialistId(reader.nextLine());
        }

        System.out.print("Item: ");
        procedure.setItem(reader.nextInt());

        System.out.print("¿Agregar este procedimiento a la orden médica? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            currentOrder.addProcedure(procedure);
            System.out.println("✅ Procedimiento agregado a la orden N° " + currentOrder.getOrderNumber());
        } else {
            System.out.println("❌ Procedimiento cancelado por el usuario.");
        }
    }

    private void diagnosticAssistance() {
        if (currentOrder == null) {
            currentOrder = new MedicalOrder();
            System.out.print("\nIngrese número de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine());
        }

        DiagnosticAid aid = new DiagnosticAid();

        System.out.print("ID ayuda diagnóstica (examen): ");
        aid.setDiagnosticId(reader.nextLine());

        System.out.print("Cantidad: ");
        aid.setQuantity(reader.nextLine());

        System.out.print("¿Requiere especialista? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            System.out.print("ID especialista: ");
            aid.setSpecialistId(reader.nextLine());
        }

        System.out.print("Item: ");
        aid.setItem(reader.nextInt());

        System.out.print("¿Desea agregar esta ayuda diagnóstica? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            currentOrder.addDiagnosticAid(aid);
            System.out.println("✅ Ayuda diagnóstica agregada correctamente a la orden N° " + currentOrder.getOrderNumber());
        } else {
            System.out.println("❌ Registro cancelado por el usuario.");
        }
    }

    private void createMedicalOrder() {
        if (currentOrder == null) {
            System.out.println("\n⚠️ No hay una orden médica activa o cargada.");
            return;
        }

        boolean hasContent =
                (currentOrder.getPrescriptions() != null && !currentOrder.getPrescriptions().isEmpty())
                        || (currentOrder.getProcedures() != null && !currentOrder.getProcedures().isEmpty())
                        || (currentOrder.getDiagnosticAids() != null && !currentOrder.getDiagnosticAids().isEmpty());

        if (!hasContent) {
            System.out.println("⚠️ La orden médica está vacía. Agregue elementos antes de guardar.");
            return;
        }

        System.out.print("¿Desea guardar esta orden médica? (si/no): ");
        if (reader.nextLine().trim().equalsIgnoreCase("si")) {
            try {
                // 🔹 Conversión manual al entity para persistir con JPA
                MedicalOrderEntity entity = new MedicalOrderEntity();
                entity.setOrderNumber(currentOrder.getOrderNumber());
                entity.setCreatedAt(LocalDateTime.now()); // ✅ CORREGIDO: compatible con LocalDateTime
                entity.setObservations("Generado automáticamente desde DoctorUseCase");

                medicalOrderRepository.save(entity);
                System.out.println("✅ Orden médica guardada correctamente.");
            } catch (Exception e) {
                System.out.println("⚠️ Error al guardar la orden médica: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Orden médica cancelada por el usuario.");
        }

        currentOrder = null;
    }

    // Utilidad general
    private String safeString(String s) {
        return s == null ? "" : s;
    }
}
