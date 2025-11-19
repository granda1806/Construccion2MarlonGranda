package app.application.usecases;

import app.domain.model.DiagnosticAid;
import app.domain.model.MedicalOrder;
import app.domain.model.Prescription;
import app.domain.model.Procedure;
import app.infrastructure.persistence.entities.*;
import app.infrastructure.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * UseCase para crear órdenes médicas de forma independiente.
 * Busca la historia clínica del paciente por su documento (no por ID).
 */
@Service
public class CreateMedicalOrderUseCase {

    private final MedicalHistoryRepository historyRepository;
    private final MedicalOrderRepository medicalOrderRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final ProcedureRepository procedureRepository;
    private final DiagnosticTestRepository diagnosticTestRepository;
    private final app.infrastructure.persistence.repository.MedicationRepository medicationRepository;
    private final DoctorRepository doctorRepository;
    private final Scanner reader = new Scanner(System.in);

    // Orden médica activa en la sesión
    private MedicalOrder currentOrder;

    @Autowired
    public CreateMedicalOrderUseCase(
            MedicalHistoryRepository historyRepository,
            MedicalOrderRepository medicalOrderRepository,
            PrescriptionRepository prescriptionRepository,
            ProcedureRepository procedureRepository,
            DiagnosticTestRepository diagnosticTestRepository,
            app.infrastructure.persistence.repository.MedicationRepository medicationRepository,
            DoctorRepository doctorRepository) {
        this.historyRepository = historyRepository;
        this.medicalOrderRepository = medicalOrderRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.procedureRepository = procedureRepository;
        this.diagnosticTestRepository = diagnosticTestRepository;
        this.medicationRepository = medicationRepository;
        this.doctorRepository = doctorRepository;
    }

    public void execute() {
        System.out.println("\n========== CREAR ORDEN MÉDICA ==========");
        System.out.print("Ingrese el documento del paciente: ");

        Long patientDocument;
        try {
            patientDocument = Long.parseLong(reader.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ El documento debe ser numérico.");
            return;
        }

        // Buscar la historia clínica por documento
        List<MedicalHistoryEntity> histories = historyRepository.findByPatientDocument(patientDocument);
        if (histories == null || histories.isEmpty()) {
            System.out.println("⚠️ No se encontró historia clínica para el paciente con documento: " + patientDocument);
            System.out.println("💡 Consejo: Primero registre una historia clínica para este paciente.");
            return;
        }

        MedicalHistoryEntity medicalHistory = histories.get(0);
        System.out.println("Historia clínica encontrada para el paciente: " + patientDocument);

        // 🏥 Solicitar el doctor que atiende el caso
        DoctorEntity doctor = getDoctorFromUser();
        if (doctor == null) {
            System.out.println("No se pudo registrar el doctor. Operación cancelada.");
            return;
        }

        // Menú para crear órdenes con validación de regla
        boolean creating = true;
        while (creating) {
            System.out.println("\n===== CREAR ORDEN MÉDICA =====");
            boolean tieneAyudaDiagnostica = currentOrder != null && currentOrder.getDiagnosticAids() != null
                    && !currentOrder.getDiagnosticAids().isEmpty();
            boolean tieneMedicamentos = currentOrder != null && currentOrder.getPrescriptions() != null
                    && !currentOrder.getPrescriptions().isEmpty();
            boolean tieneProcedimientos = currentOrder != null && currentOrder.getProcedures() != null
                    && !currentOrder.getProcedures().isEmpty();

            if (!tieneAyudaDiagnostica) {
                System.out.println("1. Agregar medicamentos (prescripciones)");
                System.out.println("2. Agregar procedimientos");
            }
            if (!tieneMedicamentos && !tieneProcedimientos) {
                System.out.println("3. Agregar pruebas diagnósticas");
            }
            System.out.println("4. Guardar orden médica");
            System.out.println("5. Cancelar");
            System.out.print("Seleccione una opción: ");

            String option = reader.nextLine().trim();

            switch (option) {
                case "1" -> {
                    if (!tieneAyudaDiagnostica) {
                        addPrescription();
                    } else {
                        System.out.println(
                                "No puede agregar medicamentos porque ya existe una ayuda diagnóstica en la orden médica.");
                    }
                }
                case "2" -> {
                    if (!tieneAyudaDiagnostica) {
                        addProcedure();
                    } else {
                        System.out.println(
                                "No puede agregar procedimientos porque ya existe una ayuda diagnóstica en la orden médica.");
                    }
                }
                case "3" -> {
                    if (!tieneMedicamentos && !tieneProcedimientos) {
                        addDiagnosticAid();
                    } else {
                        System.out.println(
                                "No puede agregar ayuda diagnóstica porque ya existe un diagnostico para el paciente.");
                    }
                }
                case "4" -> saveMedicalOrder(patientDocument, medicalHistory, doctor);
                case "5" -> {
                    System.out.println("❌ Creación cancelada.");
                    creating = false;
                }
                default -> System.out.println("⚠️ Opción no válida.");
            }
        }
    }

    private DoctorEntity getDoctorFromUser() {
        System.out.print("\nIngrese la cédula del doctor que atiende el caso: ");
        Long doctorDocument;
        try {
            doctorDocument = Long.parseLong(reader.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ La cédula del doctor debe ser numérica.");
            return null;
        }

        Optional<DoctorEntity> doctorOpt = doctorRepository.findByDocument(doctorDocument);
        if (doctorOpt.isEmpty()) {
            System.out.println("❌ No se encontró doctor registrado con cédula: " + doctorDocument);
            return null;
        }

        DoctorEntity doctor = doctorOpt.get();
        System.out.println(
                "✅ Doctor registrado: " + (doctor.getName() != null ? doctor.getName() : "Doctor " + doctorDocument));
        return doctor;
    }

    private void addPrescription() {
        if (currentOrder == null) {
            currentOrder = new MedicalOrder();
            System.out.print("\nIngrese número de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine().trim());
        }

        Prescription prescription = new Prescription();

        String medicineId;
        while (true) {
            System.out.print("Ingrese ID del medicamento (solo dígitos, máximo 6): ");
            medicineId = reader.nextLine().trim();
            if (medicineId.isEmpty()) {
                System.out.println("El ID del medicamento no puede estar vacío. Inténtelo de nuevo.");
                continue;
            }
            if (!medicineId.matches("^\\d{1,6}$")) {
                System.out.println(
                        "El ID del medicamento debe contener sólo dígitos y máximo 6 caracteres. Inténtelo de nuevo.");
                continue;
            }
            break;
        }
        prescription.setMedicineId(medicineId);

        System.out.print("Ingrese dosis: ");
        prescription.setDose(reader.nextLine().trim());

        System.out.print("Ingrese duración del tratamiento: ");
        prescription.setDuration(reader.nextLine().trim());

        System.out.print("Ingrese número de ítem: ");
        try {
            int item = Integer.parseInt(reader.nextLine().trim());
            if (currentOrder.containsItem(String.valueOf(item))) {
                System.out.println("⚠️ Ya existe un elemento con ese número de ítem en esta orden.");
                return;
            }
            prescription.setItem(item);
            currentOrder.addPrescription(prescription);
            System.out.println("✅ Medicamento agregado a la orden: " + medicineId);
        } catch (NumberFormatException e) {
            System.out.println("❌ El ítem debe ser un número válido.");
        }
    }

    private void addProcedure() {
        if (currentOrder == null) {
            currentOrder = new MedicalOrder();
            System.out.print("\nIngrese número de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine().trim());
        }

        Procedure procedure = new Procedure();

        System.out.print("Ingrese ID del procedimiento: ");
        String procedureId = reader.nextLine().trim();
        if (procedureId.isEmpty()) {
            System.out.println("❌ El ID no puede estar vacío.");
            return;
        }
        procedure.setProcedureId(procedureId);

        System.out.print("Ingrese cantidad: ");
        try {
            procedure.setQuantity(Integer.parseInt(reader.nextLine().trim()));
        } catch (NumberFormatException e) {
            System.out.println("❌ La cantidad debe ser un número válido.");
            return;
        }

        System.out.print("Ingrese frecuencia: ");
        procedure.setFrequency(reader.nextLine().trim());

        System.out.print("Ingrese costo: ");
        try {
            procedure.setCost(Double.parseDouble(reader.nextLine().trim()));
        } catch (NumberFormatException e) {
            System.out.println("❌ El costo debe ser un número válido.");
            return;
        }

        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine().trim().toLowerCase();
        if (req.equals("si")) {
            System.out.print("ID del especialista: ");
            procedure.setSpecialistId(reader.nextLine().trim());
            procedure.setRequiresSpecialist(true);
        } else {
            procedure.setRequiresSpecialist(false);
        }

        System.out.print("Ingrese número de ítem: ");
        try {
            int item = Integer.parseInt(reader.nextLine().trim());
            if (currentOrder.containsItem(String.valueOf(item))) {
                System.out.println("⚠️ Ya existe un elemento con ese número de ítem en esta orden.");
                return;
            }
            procedure.setItem(item);
            currentOrder.addProcedure(procedure);
            System.out.println("✅ Procedimiento agregado a la orden: " + procedureId);
        } catch (NumberFormatException e) {
            System.out.println("❌ El ítem debe ser un número válido.");
        }
    }

    private void addDiagnosticAid() {
        if (currentOrder == null) {
            currentOrder = new MedicalOrder();
            System.out.print("\nIngrese número de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine().trim());
        }

        DiagnosticAid aid = new DiagnosticAid();

        System.out.print("Ingrese ID de la prueba diagnóstica: ");
        String diagnosticId = reader.nextLine().trim();
        if (diagnosticId.isEmpty()) {
            System.out.println("❌ El ID no puede estar vacío.");
            return;
        }
        aid.setDiagnosticId(diagnosticId);

        System.out.print("Ingrese cantidad: ");
        aid.setQuantity(reader.nextLine().trim());

        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine().trim().toLowerCase();
        if (req.equals("si")) {
            System.out.print("ID del especialista: ");
            aid.setSpecialistId(reader.nextLine().trim());
        }

        System.out.print("Ingrese número de ítem: ");
        try {
            String item = reader.nextLine().trim();
            if (currentOrder.containsItem(item)) {
                System.out.println("⚠️ Ya existe un elemento con ese número de ítem en esta orden.");
                return;
            }
            aid.setItem(item);
            currentOrder.addDiagnosticAid(aid);
            System.out.println("✅ Prueba diagnóstica agregada a la orden: " + diagnosticId);
        } catch (Exception e) {
            System.out.println("❌ Error al agregar la prueba diagnóstica: " + e.getMessage());
        }
    }

    private void saveMedicalOrder(Long patientDocument, MedicalHistoryEntity medicalHistory, DoctorEntity doctor) {
        if (currentOrder == null) {
            System.out.println("⚠️ No hay una orden médica activa.");
            return;
        }

        boolean hasContent = (currentOrder.getPrescriptions() != null && !currentOrder.getPrescriptions().isEmpty())
                || (currentOrder.getProcedures() != null && !currentOrder.getProcedures().isEmpty())
                || (currentOrder.getDiagnosticAids() != null && !currentOrder.getDiagnosticAids().isEmpty());

        if (!hasContent) {
            System.out.println("⚠️ La orden médica está vacía. Agregue elementos antes de guardar.");
            return;
        }

        System.out.println("\n" + currentOrder);
        System.out.print("\n¿Desea guardar esta orden médica? (si/no): ");
        if (!reader.nextLine().trim().equalsIgnoreCase("si")) {
            System.out.println("❌ Orden médica cancelada.");
            return;
        }

        try {
            // 1. Guardar la orden médica principal
            MedicalOrderEntity entity = new MedicalOrderEntity();
            entity.setOrderNumber(currentOrder.getOrderNumber());
            entity.setCreatedAt(LocalDateTime.now());
            entity.setObservations(
                    "Orden médica creada por: " + (doctor.getName() != null ? doctor.getName() : "Doctor"));
            entity.setPatientDocument(patientDocument);
            entity.setMedicalHistory(medicalHistory);
            entity.setDoctor(doctor); // ✅ Guardar el doctor

            MedicalOrderEntity savedOrder = medicalOrderRepository.save(entity);
            System.out.println("\n✅ Orden médica guardada (ID: " + savedOrder.getId() + ")");
            System.out.println("✅ Documento del paciente: " + patientDocument);
            System.out.println("✅ Doctor responsable: " + doctor.getName());

            // 2. Guardar prescripciones (medicamentos)
            if (currentOrder.getPrescriptions() != null && !currentOrder.getPrescriptions().isEmpty()) {
                int count = 0;
                for (Prescription prescription : currentOrder.getPrescriptions()) {
                    PrescriptionEntity prescriptionEntity = new PrescriptionEntity();
                    prescriptionEntity.setMedicineId(prescription.getMedicineId());
                    prescriptionEntity.setDose(prescription.getDose());
                    prescriptionEntity.setDuration(prescription.getDuration());
                    prescriptionEntity.setItem(prescription.getItem());
                    prescriptionEntity.setMedicalOrder(savedOrder);
                    prescriptionRepository.save(prescriptionEntity);
                    // Intentar obtener nombre y costo del medicamento para mostrar información más
                    // completa
                    String medInfo = prescription.getMedicineId();
                    try {
                        long medIdLong = Long.parseLong(prescription.getMedicineId());
                        var medOpt = medicationRepository.findById(medIdLong);
                        if (medOpt.isPresent()) {
                            var med = medOpt.get();
                            medInfo = med.getName() + " (ID: " + med.getId() + ") - Costo: $" + med.getCost();
                        }
                    } catch (NumberFormatException ignored) {
                        // Si el ID no es numérico, dejamos medInfo como está
                    } catch (Exception ex) {
                        // No interrumpir el guardado si hay error al consultar medicamento
                    }

                    System.out.println("  ✅ Medicamento guardado: " + medInfo + " - Dosis: " + prescription.getDose());
                    count++;
                }
                System.out.println("✅ Total: " + count + " medicamentos guardados");
            }

            // 3. Guardar procedimientos
            if (currentOrder.getProcedures() != null && !currentOrder.getProcedures().isEmpty()) {
                int count = 0;
                for (Procedure procedure : currentOrder.getProcedures()) {
                    ProcedureEntity procedureEntity = new ProcedureEntity();
                    procedureEntity.setProcedureId(procedure.getProcedureId());
                    procedureEntity.setQuantity(procedure.getQuantity());
                    procedureEntity.setFrequency(procedure.getFrequency());
                    procedureEntity.setCost(procedure.getCost());
                    procedureEntity.setRequiresSpecialist(procedure.isRequiresSpecialist());
                    procedureEntity.setSpecialistTypeId(procedure.getSpecialistId());
                    procedureEntity.setMedicalOrder(savedOrder);
                    procedureRepository.save(procedureEntity);
                    System.out.println("  ✅ Procedimiento guardado: " + procedure.getProcedureId() + " - Cantidad: "
                            + procedure.getQuantity());
                    count++;
                }
                System.out.println("✅ Total: " + count + " procedimientos guardados");
            }

            // 4. Guardar pruebas diagnósticas
            if (currentOrder.getDiagnosticAids() != null && !currentOrder.getDiagnosticAids().isEmpty()) {
                int count = 0;
                for (DiagnosticAid aid : currentOrder.getDiagnosticAids()) {
                    DiagnosticTestEntity testEntity = new DiagnosticTestEntity();
                    testEntity.setTestId(aid.getDiagnosticId());

                    try {
                        int itemNum = Integer.parseInt(aid.getItem());
                        testEntity.setItem(itemNum);
                    } catch (NumberFormatException e) {
                        testEntity.setItem(0);
                    }

                    testEntity.setMedicalOrder(savedOrder);
                    diagnosticTestRepository.save(testEntity);
                    System.out.println("  ✅ Prueba diagnóstica guardada: " + aid.getDiagnosticId());
                    count++;
                }
                System.out.println("✅ Total: " + count + " pruebas diagnósticas guardadas");
            }

            System.out.println("\n✅ Orden médica completa guardada correctamente en BD.");
            currentOrder = null;

        } catch (Exception e) {
            System.out.println("⚠️ Error al guardar la orden médica: " + e.getMessage());
            e.printStackTrace();
        }
    }
}