package app.application.usecases;

import app.domain.model.*;
import java.util.Scanner;
import app.infrastructure.persistence.repository.InMemoryMedicalOrderRepository;
import app.infrastructure.persistence.repository.MedicalOrderRepository;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.repository.MedicalHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.sql.Date;

@Service
public class DoctorUseCase {

    private final Scanner reader = new Scanner(System.in);

    @Autowired
    private MedicalHistoryRepository historyRepository; // ✅ Inyectado correctamente

    private MedicalOrder currentOrder;

    public void manageMedicalHistory() {

        System.out.print("\nIngrese el ID del paciente: ");
        Long patientId;
        try {
            patientId = Long.parseLong(reader.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("El ID del paciente debe ser numérico.");
            return;
        }

        boolean exists = historyRepository.existsByPatientId(patientId);

        if (!exists) {
            System.out.println("No se encontró historia clínica para este paciente. Cree una primero (opción 1).");
            return;
        }

        boolean alternatemenu = true;

        while (alternatemenu) {
            System.out.println("\nSUBMENÚ HISTORIA CLÍNICA");
            System.out.println("1. Agregar información de la consulta");
            System.out.println("2. Recetar medicamentos");
            System.out.println("3. Procedimiento médico");
            System.out.println("4. Ayuda diagnóstica");
            System.out.println("5. Crear orden médica");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" -> consultationInformation(patientId);
                case "2" -> prescribeMedicine();
                case "3" -> medicalProcedure();
                case "4" -> diagnosticAssistance();
                case "5" -> createMedicalOrder();
                case "6" -> {
                    System.out.println("Volviendo al menú principal...");
                    alternatemenu = false;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void consultationInformation(Long patientId) {

        System.out.println("\nAGREGAR INFORMACIÓN DE LA CONSULTA");

        System.out.print("Fecha (YYYY-MM-DD): ");
        String dateStr = reader.nextLine();
        Date date = Date.valueOf(dateStr);

        System.out.print("Cédula del médico: ");
        Long doctorId;
        try {
            doctorId = Long.parseLong(reader.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("El ID del médico debe ser numérico.");
            return;
        }

        System.out.print("Motivo consulta: ");
        String reason = reader.nextLine();

        System.out.print("Sintomatología: ");
        String symptoms = reader.nextLine();

        System.out.print("Diagnóstico: ");
        String diagnosis = reader.nextLine();

        MedicalHistoryEntity entity = new MedicalHistoryEntity();
        entity.setPatientId(patientId);
        entity.setDoctorId(doctorId);
        entity.setDate(date);
        entity.setReason(reason);
        entity.setSymptoms(symptoms);
        entity.setDiagnosis(diagnosis);

        historyRepository.save(entity);
        System.out.println("Historia clínica guardada correctamente.");
    }

    private void prescribeMedicine() {
        if (currentOrder == null) {
            System.out.println("\nNo hay una orden médica activa.");
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
        prescription.setItem(reader.nextLine());

        if (currentOrder.containsItem(prescription.getItem())) {
            System.out.println("Ya existe un elemento con ese número de ítem en esta orden.");
            return;
        }

        currentOrder.addPrescription(prescription);
        System.out.println("Medicamento recetado correctamente.");
    }

    private void medicalProcedure() {

        if (currentOrder == null) {
            currentOrder = new MedicalOrder();
            System.out.print("\nIngrese número de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine());
        }

        Procedure procedure = new Procedure();

        System.out.print("ID del procedimiento: ");
        String procedureId = reader.nextLine();
        while (procedureId.isEmpty()) {
            System.out.print("ID no puede estar vacío. Ingrese ID del procedimiento: ");
            procedureId = reader.nextLine();
        }

        procedure.setProcedureId(procedureId);

        System.out.print("Cantidad: ");
        procedure.setQuantity(reader.nextLine());

        System.out.print("Frecuencia: ");
        procedure.setFrequency(reader.nextLine());

        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine();

        if (req.equalsIgnoreCase("si")) {
            System.out.print("ID del especialista: ");
            procedure.setSpecialistId(reader.nextLine());
        }

        System.out.print("Item: ");
        procedure.setItem(reader.nextLine());

        System.out.print("¿Desea agregar este procedimiento a la orden médica? (si/no): ");
        String confirm = reader.nextLine();

        if (confirm.equalsIgnoreCase("si")) {
            currentOrder.addProcedure(procedure);
            System.out.println("Procedimiento agregado correctamente a la orden médica N°: "
                    + currentOrder.getOrderNumber());
        } else {
            System.out.println("Procedimiento cancelado por el usuario.");
        }
    }

    private void diagnosticAssistance() {

        if ((currentOrder != null)
                && (!currentOrder.getPrescriptions().isEmpty() || !currentOrder.getProcedures().isEmpty())) {
            System.out.println("No puede agregar una ayuda diagnóstica a una orden que ya contiene medicamentos o procedimientos.");
            return;
        }

        if (currentOrder == null) {
            currentOrder = new MedicalOrder();
            System.out.print("\nIngrese número de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine());
        }

        DiagnosticAid aid = new DiagnosticAid();

        System.out.print("ID ayuda diagnóstica (examen): ");
        String diagnosticId = reader.nextLine();

        while (diagnosticId.isEmpty()) {
            System.out.print("El ID no puede estar vacío. Ingrese nuevamente: ");
            diagnosticId = reader.nextLine();
        }

        aid.setDiagnosticId(diagnosticId);

        System.out.print("Cantidad: ");
        aid.setQuantity(reader.nextLine());

        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine();

        if (req.equalsIgnoreCase("si")) {
            System.out.print("ID especialista: ");
            aid.setSpecialistId(reader.nextLine());
        }

        System.out.print("Item: ");
        aid.setItem(reader.nextLine());

        System.out.print("¿Desea agregar esta ayuda diagnóstica a la orden médica? (si/no): ");
        String confirm = reader.nextLine();

        if (confirm.equalsIgnoreCase("si")) {
            currentOrder.addDiagnosticAid(aid);
            System.out.println("Ayuda diagnóstica agregada correctamente a la orden N°: "
                    + currentOrder.getOrderNumber());
        } else {
            System.out.println("Registro cancelado por el usuario.");
        }
    }

    private void createMedicalOrder() {

        if (currentOrder == null) {
            System.out.println("\nNo hay una orden médica activa o cargada.");
            return;
        }

        boolean hasContent =
                (currentOrder.getPrescriptions() != null && !currentOrder.getPrescriptions().isEmpty())
                || (currentOrder.getProcedures() != null && !currentOrder.getProcedures().isEmpty())
                || (currentOrder.getDiagnosticAids() != null && !currentOrder.getDiagnosticAids().isEmpty());

        if (!hasContent) {
            System.out.println("\nLa orden médica no contiene medicamentos, procedimientos ni ayudas diagnósticas.");
            System.out.println("Agregue al menos un elemento antes de finalizar la orden.");
            return;
        }

        System.out.println("\nORDEN MÉDICA FINALIZADA:");
        System.out.println(currentOrder);

        System.out.print("\n¿Desea guardar esta orden médica? (si/no): ");
        String confirm = reader.nextLine();

        if (confirm.trim().equalsIgnoreCase("si")) {
            MedicalOrderRepository repository = new InMemoryMedicalOrderRepository();
            repository.save(currentOrder);
            System.out.println("Orden médica guardada correctamente.");
        } else {
            System.out.println("Orden médica cancelada por el usuario.");
        }

        currentOrder = null;
    }

    public void createMedicalRecord() {

        System.out.println("\nCREAR REGISTRO MÉDICO:");
        System.out.print("\nIngrese el ID del paciente: ");
        Long patientId;
        try {
            patientId = Long.parseLong(reader.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("El ID del paciente debe ser numérico.");
            return;
        }

        boolean exists = historyRepository.existsByPatientId(patientId);

        if (!exists) {
            System.out.println("No se encontró historia clínica para este paciente. Cree una primero (opción 1).");
            return;
        }

        System.out.print("Ingrese la fecha del registro (YYYY-MM-DD): ");
        String date = reader.nextLine();

        System.out.print("Cédula del médico: ");
        Long doctorId;
        try {
            doctorId = Long.parseLong(reader.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("El ID del médico debe ser numérico.");
            return;
        }

        System.out.print("Motivo de consulta: ");
        String reason = reader.nextLine();

        System.out.print("Síntomas: ");
        String symptoms = reader.nextLine();

        System.out.print("Diagnóstico: ");
        String diagnosis = reader.nextLine();

        if (patientId == null || date.isEmpty() || doctorId == null || reason.isEmpty() || diagnosis.isEmpty()) {
            System.out.println("Error: campos obligatorios vacíos.");
            return;
        }

        Date dateSql = Date.valueOf(date);

        MedicalHistoryEntity entity = new MedicalHistoryEntity();
        entity.setPatientId(patientId);
        entity.setDoctorId(doctorId);
        entity.setDate(dateSql);
        entity.setReason(reason);
        entity.setSymptoms(symptoms);
        entity.setDiagnosis(diagnosis);

        historyRepository.save(entity);
        System.out.println("Registro médico guardado correctamente.");
    }

    public void updateMedicalRecord() {

        System.out.print("\nIngrese el ID del paciente: ");
        Long patientId;
        try {
            patientId = Long.parseLong(reader.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("El ID del paciente debe ser numérico.");
            return;
        }

        System.out.print("Ingrese la fecha del registro a actualizar (YYYY-MM-DD): ");
        String dateStr = reader.nextLine();

        Date date;
        try {
            date = Date.valueOf(dateStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha inválido. Use YYYY-MM-DD.");
            return;
        }

        Optional<MedicalHistoryEntity> optional = historyRepository.findByPatientIdAndDate(patientId, date);

        if (optional.isEmpty()) {
            System.out.println("No se encontró un registro para esa fecha.");
            return;
        }

        MedicalHistoryEntity record = optional.get();

        System.out.println("\nRegistro actual encontrado:");
        System.out.println("Motivo: " + safeString(record.getReason()));
        System.out.println("Síntomas: " + safeString(record.getSymptoms()));
        System.out.println("Diagnóstico: " + safeString(record.getDiagnosis()));

        System.out.print("\n¿Desea actualizar el motivo? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            System.out.print("Nuevo motivo: ");
            record.setReason(reader.nextLine());
        }

        System.out.print("¿Desea actualizar los síntomas? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            System.out.print("Nuevos síntomas: ");
            record.setSymptoms(reader.nextLine());
        }

        System.out.print("¿Desea actualizar el diagnóstico? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            System.out.print("Nuevo diagnóstico: ");
            record.setDiagnosis(reader.nextLine());
        }

        historyRepository.save(record);
        System.out.println("\nRegistro médico actualizado correctamente.");
    }

    private String safeString(String s) {
        return s == null ? "" : s;
    }
}
