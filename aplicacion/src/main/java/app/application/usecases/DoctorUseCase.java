package app.application.usecases;

import app.domain.model.DiagnosticAid;
import app.domain.model.MedicalOrder;
import app.domain.model.Prescription;
import app.domain.model.Procedure;
import app.infrastructure.persistence.entities.*;
import app.infrastructure.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DoctorUseCase {
    /**
     * Permite al doctor procesar el resultado de una ayuda diagnóstica,
     * crear un nuevo registro con diagnóstico y recetar medicamentos y/o
     * procedimientos.
     * Este método debe ser llamado manualmente por el doctor tras ver el resultado.
     */
    public void processDiagnosticResult(Long patientId) {

        System.out.println("\n--- Procesar resultado de ayuda diagnóstica ---");

        // Solicitar diagnóstico
        System.out.print("Ingrese diagnóstico confirmado: ");
        String diagnosis = reader.nextLine();

        // Crear nueva orden médica
        MedicalOrder newOrder = new MedicalOrder();
        System.out.print("Ingrese número de nueva orden médica: ");
        newOrder.setOrderNumber(reader.nextLine());

        // Validación: ¿Se va a anexar una ayuda diagnóstica?
        boolean anexarAyudaDiagnostica = false;
        System.out.print("¿Va a anexar una ayuda diagnóstica en esta orden? (si/no): ");
        String respuestaAyuda = reader.nextLine();
        if (respuestaAyuda.trim().equalsIgnoreCase("si")) {
            anexarAyudaDiagnostica = true;
        }

        // Permitir agregar medicamentos solo si NO se va a anexar ayuda diagnóstica
        if (!anexarAyudaDiagnostica) {
            boolean addMore = true;
            while (addMore) {
                System.out.print("¿Desea recetar medicamento? (si/no): ");
                String opt = reader.nextLine();
                if (opt.equalsIgnoreCase("si")) {
                    Prescription prescription = new Prescription();
                    System.out.print("ID del medicamento: ");
                    prescription.setMedicineId(reader.nextLine());
                    System.out.print("Dosis: ");
                    prescription.setDose(reader.nextLine());
                    System.out.print("Duración del tratamiento: ");
                    prescription.setDuration(reader.nextLine());
                    System.out.print("Número de ítem: ");
                    int item = Integer.parseInt(reader.nextLine());
                    if (newOrder.containsItem(String.valueOf(item))) {
                        System.out.println("Ya existe un elemento con ese número de ítem en esta orden.");
                    } else {
                        prescription.setItem(item);
                        newOrder.addPrescription(prescription);
                        System.out.println("Medicamento recetado correctamente.");
                    }
                } else {
                    addMore = false;
                }
            }
        } else {
            System.out.println(
                    "No se pueden agregar medicamentos porque se va a anexar una ayuda diagnóstica en esta orden médica.");
        }

        // Permitir agregar procedimientos solo si NO se va a anexar ayuda diagnóstica
        if (!anexarAyudaDiagnostica) {
            boolean addMore = true;
            while (addMore) {
                System.out.print("¿Desea agregar procedimiento? (si/no): ");
                String opt = reader.nextLine();
                if (opt.equalsIgnoreCase("si")) {
                    Procedure procedure = new Procedure();
                    System.out.print("ID del procedimiento: ");
                    procedure.setProcedureId(reader.nextLine());
                    System.out.print("Cantidad: ");
                    procedure.setQuantity(Integer.parseInt(reader.nextLine()));
                    System.out.print("Frecuencia: ");
                    procedure.setFrequency(reader.nextLine());
                    System.out.print("¿Requiere especialista? (si/no): ");
                    String req = reader.nextLine();
                    if (req.equalsIgnoreCase("si")) {
                        System.out.print("ID del especialista: ");
                        procedure.setSpecialistId(reader.nextLine());
                    }
                    System.out.print("Número de ítem: ");
                    int item = Integer.parseInt(reader.nextLine());
                    if (newOrder.containsItem(String.valueOf(item))) {
                        System.out.println("Ya existe un elemento con ese número de ítem en esta orden.");
                    } else {
                        procedure.setItem(item);
                        newOrder.addProcedure(procedure);
                        System.out.println("Procedimiento agregado correctamente.");
                    }
                } else {
                    addMore = false;
                }
            }
        } else {
            System.out.println(
                    "No se pueden agregar procedimientos porque se va a anexar una ayuda diagnóstica en esta orden médica.");
        }

        // Guardar la nueva orden médica
        try {
            java.util.List<MedicalHistoryEntity> histories = historyRepository.findByPatientId(patientId);
            MedicalHistoryEntity medicalHistory = (histories != null && !histories.isEmpty()) ? histories.get(0) : null;

            MedicalOrderEntity entity = new MedicalOrderEntity();
            entity.setOrderNumber(newOrder.getOrderNumber());
            entity.setCreatedAt(LocalDateTime.now());
            entity.setObservations("Orden generada tras resultado de ayuda diagnóstica. Diagnóstico: " + diagnosis);
            entity.setPatientDocument(patientId);
            entity.setMedicalHistory(medicalHistory);

            MedicalOrderEntity savedOrder = medicalOrderRepository.save(entity);

            // Guardar medicamentos
            if (newOrder.getPrescriptions() != null && !newOrder.getPrescriptions().isEmpty()) {
                for (Prescription prescription : newOrder.getPrescriptions()) {
                    PrescriptionEntity prescriptionEntity = new PrescriptionEntity();
                    prescriptionEntity.setMedicineId(prescription.getMedicineId());
                    prescriptionEntity.setDose(prescription.getDose());
                    prescriptionEntity.setDuration(prescription.getDuration());
                    prescriptionEntity.setItem(prescription.getItem());
                    prescriptionEntity.setMedicalOrder(savedOrder);
                    prescriptionRepository.save(prescriptionEntity);
                }
                System.out.println("✅ " + newOrder.getPrescriptions().size() + " medicamentos guardados");
            }

            // Guardar procedimientos
            if (newOrder.getProcedures() != null && !newOrder.getProcedures().isEmpty()) {
                for (Procedure procedure : newOrder.getProcedures()) {
                    ProcedureEntity procedureEntity = new ProcedureEntity();
                    procedureEntity.setProcedureId(procedure.getProcedureId());
                    procedureEntity.setQuantity(procedure.getQuantity());
                    procedureEntity.setFrequency(procedure.getFrequency());
                    procedureEntity.setCost(procedure.getCost());
                    procedureEntity.setRequiresSpecialist(procedure.isRequiresSpecialist());
                    procedureEntity.setSpecialistTypeId(procedure.getSpecialistId());
                    procedureEntity.setMedicalOrder(savedOrder);
                    procedureRepository.save(procedureEntity);
                }
                System.out.println("✅ " + newOrder.getProcedures().size() + " procedimientos guardados");
            }

            System.out.println("✅ Nueva orden médica guardada correctamente tras resultado de ayuda diagnóstica.");
        } catch (Exception e) {
            System.out.println("⚠️ Error al guardar la nueva orden médica: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private final Scanner reader = new Scanner(System.in);

    @Autowired
    private MedicalHistoryRepository historyRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalOrderRepository medicalOrderRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private DiagnosticTestRepository diagnosticTestRepository;

    // Orden médica activa en la sesión del médico
    private MedicalOrder currentOrder;

    // ================== MENU PRINCIPAL ==================
    public void manageMedicalHistory() {
        System.out.print("\nIngrese el ID del paciente: ");
        Long patientId;

        try {
            patientId = Long.parseLong(reader.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El ID del paciente debe ser numérico.");
            return;
        }

        boolean exists = historyRepository.existsByPatientId(patientId);
        if (!exists) {
            System.out.println("No se encontró historia clínica para este paciente.");
            return;
        }

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n===== SUBMENU HISTORIA CLINICA =====");
            System.out.println("1. Agregar informacion de la consulta");

            // Mostrar opciones según el estado de la orden médica actual
            boolean ayudaDiagnosticaAgregada = false;
            if (currentOrder != null && currentOrder.getDiagnosticAids() != null
                    && !currentOrder.getDiagnosticAids().isEmpty()) {
                ayudaDiagnosticaAgregada = true;
            }

            if (!ayudaDiagnosticaAgregada) {
                System.out.println("2. Recetar medicamentos");
                System.out.println("3. Procedimiento medico");
            }
            System.out.println("4. Ayuda diagnostica");
            System.out.println("5. Crear orden medica");
            System.out.println("6. Volver al menu principal");
            System.out.print("Seleccione una opción: ");

            String option = reader.nextLine().trim();

            switch (option) {
                case "1" -> consultationInformation(patientId);
                case "2" -> {
                    if (!ayudaDiagnosticaAgregada) {
                        prescribeMedicine();
                    } else {
                        System.out.println(
                                "No puede agregar medicamentos porque ya existe una ayuda diagnóstica en la orden médica.");
                    }
                }
                case "3" -> {
                    if (!ayudaDiagnosticaAgregada) {
                        medicalProcedure();
                    } else {
                        System.out.println(
                                "No puede agregar procedimientos porque ya existe una ayuda diagnóstica en la orden médica.");
                    }
                }
                case "4" -> diagnosticAssistance();
                case "5" -> createMedicalOrder();
                case "6" -> {
                    System.out.println("↩️ Volviendo al menú principal...");
                    inMenu = false;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // ================== METODOS ==================
    // ================== MENU CREAR ORDEN MÉDICA ==================
    public void crearOrdenMedicaMenu() {
        currentOrder = new MedicalOrder();
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n===== CREAR ORDEN MÉDICA =====");
            System.out.println("Número de orden actual: "
                    + (currentOrder.getOrderNumber() != null ? currentOrder.getOrderNumber() : "No registrada"));

            boolean tieneAyudaDiagnostica = currentOrder.getDiagnosticAids() != null
                    && !currentOrder.getDiagnosticAids().isEmpty();
            boolean tieneMedicamentos = currentOrder.getPrescriptions() != null
                    && !currentOrder.getPrescriptions().isEmpty();
            boolean tieneProcedimientos = currentOrder.getProcedures() != null
                    && !currentOrder.getProcedures().isEmpty();

            // Mostrar opciones según el estado actual
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

            String opcion = reader.nextLine().trim();

            switch (opcion) {
                case "1" -> {
                    if (!tieneAyudaDiagnostica) {
                        prescribeMedicine();
                    } else {
                        System.out.println(
                                "No puede agregar medicamentos porque ya existe una ayuda diagnóstica en la orden médica.");
                    }
                }
                case "2" -> {
                    if (!tieneAyudaDiagnostica) {
                        medicalProcedure();
                    } else {
                        System.out.println(
                                "No puede agregar procedimientos porque ya existe una ayuda diagnóstica en la orden médica.");
                    }
                }
                case "3" -> {
                    if (!tieneMedicamentos && !tieneProcedimientos) {
                        diagnosticAssistance();
                    } else {
                        System.out.println(
                                "No puede agregar ayuda diagnóstica porque ya existen medicamentos o procedimientos en la orden médica.");
                    }
                }
                case "4" -> {
                    createMedicalOrder();
                    inMenu = false;
                }
                case "5" -> {
                    System.out.println("Operación cancelada. Volviendo al menú anterior.");
                    currentOrder = null;
                    inMenu = false;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void consultationInformation(Long patientId) {
        System.out.println("\n AGREGAR INFORMACION DE LA CONSULTA");

        System.out.print("Fecha (YYYY-MM-DD): ");
        LocalDate date;
        try {
            date = LocalDate.parse(reader.nextLine().trim());
        } catch (Exception e) {
            System.out.println(" Formato de fecha invalido. Use YYYY-MM-DD.");
            return;
        }

        System.out.print("Cedula del medico: ");
        Long doctorDocument;
        try {
            doctorDocument = Long.parseLong(reader.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(" La cedula del medico debe ser numerica.");
            return;
        }

        Optional<DoctorEntity> doctorOpt = doctorRepository.findByDocument(doctorDocument);
        if (doctorOpt.isEmpty()) {
            System.out.println(" No existe un medico registrado con cedula " + doctorDocument);
            return;
        }

        System.out.print("Motivo de consulta: ");
        String reason = reader.nextLine();

        System.out.print("Sintomatología: ");
        String symptoms = reader.nextLine();

        System.out.print("Diagnostico: ");
        String diagnosis = reader.nextLine();

        MedicalHistoryEntity entity = new MedicalHistoryEntity();
        entity.setDoctor(doctorOpt.get());
        entity.setPatientId(patientId);
        entity.setDate(java.sql.Date.valueOf(date.toString()));
        entity.setReasonForConsultation(reason);
        entity.setSymptoms(symptoms);
        entity.setDiagnosis(diagnosis);
        entity.setObservations("Registro ingresado por el doctor");

        try {
            historyRepository.save(entity);
            System.out.println("Historia clinica guardada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar la historia clinica: " + e.getMessage());
        }
    }

    private void prescribeMedicine() {
        if (currentOrder == null) {
            System.out.println("\nNo hay una orden medica activa. Cree una primero.");
            return;
        }
        // Validación: no permitir medicamentos si hay ayuda diagnóstica
        if (currentOrder.getDiagnosticAids() != null && !currentOrder.getDiagnosticAids().isEmpty()) {
            System.out.println("No se pueden recetar medicamentos en una orden que contiene ayuda diagnóstica.");
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
        reader.nextLine(); // limpiar buffer

        if (currentOrder.containsItem(String.valueOf(item))) {
            System.out.println("Ya existe un elemento con ese número de ítem en esta orden.");
            return;
        }

        prescription.setItem(item);
        currentOrder.addPrescription(prescription);
        System.out.println("Medicamento recetado correctamente.");
    }

    private void medicalProcedure() {
        if (currentOrder == null) {
            currentOrder = new MedicalOrder();
            System.out.print("\nIngrese número de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine());
        }
        // Validación: no permitir procedimientos si hay ayuda diagnóstica
        if (currentOrder.getDiagnosticAids() != null && !currentOrder.getDiagnosticAids().isEmpty()) {
            System.out.println("No se pueden recetar procedimientos en una orden que contiene ayuda diagnóstica.");
            return;
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
        try {
            procedure.setQuantity(Integer.parseInt(reader.nextLine().trim()));
        } catch (NumberFormatException e) {
            System.out.println("⚠️ La cantidad debe ser un número válido.");
            return;
        }

        System.out.print("Frecuencia: ");
        procedure.setFrequency(reader.nextLine());

        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine();

        if (req.equalsIgnoreCase("si")) {
            System.out.print("ID del especialista: ");
            procedure.setSpecialistId(reader.nextLine());
        }

        System.out.print("Item: ");
        int item = reader.nextInt();
        reader.nextLine(); // limpiar buffer

        if (currentOrder.containsItem(String.valueOf(item))) {
            System.out.println("⚠️ Ya existe un elemento con ese número de ítem en esta orden.");
            return;
        }

        procedure.setItem(item);

        System.out.print("¿Desea agregar este procedimiento a la orden médica? (si/no): ");
        String confirm = reader.nextLine();

        if (confirm.equalsIgnoreCase("si")) {
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
        // Validación: no permitir ayuda diagnóstica si hay medicamentos o
        // procedimientos
        boolean hasMedicamentos = currentOrder.getPrescriptions() != null && !currentOrder.getPrescriptions().isEmpty();
        boolean hasProcedimientos = currentOrder.getProcedures() != null && !currentOrder.getProcedures().isEmpty();
        if (hasMedicamentos || hasProcedimientos) {
            System.out.println(
                    "No se puede agregar ayuda diagnóstica en una orden que contiene medicamentos o procedimientos.");
            return;
        }

        DiagnosticAid aid = new DiagnosticAid();

        System.out.print("ID ayuda diagnóstica (examen): ");
        String diagnosticId = reader.nextLine().trim();
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
        int item = reader.nextInt();
        reader.nextLine(); // limpiar buffer

        if (currentOrder.containsItem(String.valueOf(item))) {
            System.out.println("⚠️ Ya existe un elemento con ese número de ítem en esta orden.");
            return;
        }

        aid.setItem(String.valueOf(item));

        System.out.print("¿Desea agregar esta ayuda diagnóstica? (si/no): ");
        String confirm = reader.nextLine();

        if (confirm.equalsIgnoreCase("si")) {
            currentOrder.addDiagnosticAid(aid);
            System.out.println(
                    "✅ Ayuda diagnóstica agregada correctamente a la orden N° " + currentOrder.getOrderNumber());
            // Bloquear la adición posterior de medicamentos y procedimientos
            System.out
                    .println("A partir de ahora no podrá agregar medicamentos ni procedimientos a esta orden médica.");
        } else {
            System.out.println("❌ Registro cancelado por el usuario.");
        }
    }

    private void createMedicalOrder() {
        if (currentOrder == null) {
            System.out.println("\n⚠️ No hay una orden médica activa o cargada.");
            return;
        }

        boolean hasContent = (currentOrder.getPrescriptions() != null && !currentOrder.getPrescriptions().isEmpty())
                || (currentOrder.getProcedures() != null && !currentOrder.getProcedures().isEmpty())
                || (currentOrder.getDiagnosticAids() != null && !currentOrder.getDiagnosticAids().isEmpty());

        if (!hasContent) {
            System.out.println("⚠️ La orden médica está vacía. Agregue elementos antes de guardar.");
            return;
        }

        System.out.println("\nORDEN MÉDICA A GUARDAR:");
        System.out.println(currentOrder);

        System.out.print("\n¿Desea guardar esta orden médica? (si/no): ");
        if (reader.nextLine().trim().equalsIgnoreCase("si")) {
            try {
                // 0. Obtener la historia clínica del paciente para vincularla
                System.out.print("\nIngrese la cédula del paciente: ");
                Long patientDocument = Long.parseLong(reader.nextLine().trim());

                java.util.List<MedicalHistoryEntity> histories = historyRepository
                        .findByPatientDocument(patientDocument);
                if (histories == null || histories.isEmpty()) {
                    System.out.println(
                            "⚠️ No se encontró historia clínica para ese paciente. Se creará solo la orden médica sin vincular.");
                }

                MedicalHistoryEntity medicalHistory = (histories != null && !histories.isEmpty()) ? histories.get(0)
                        : null;

                // 1. Guardar la orden médica principal
                MedicalOrderEntity entity = new MedicalOrderEntity();
                entity.setOrderNumber(currentOrder.getOrderNumber());
                entity.setCreatedAt(LocalDateTime.now());
                entity.setObservations("Generado automáticamente desde DoctorUseCase");
                entity.setPatientDocument(patientDocument); // 💾 GUARDAR DOCUMENTO DEL PACIENTE
                entity.setMedicalHistory(medicalHistory); // ✅ VINCULAR CON HISTORIA CLÍNICA

                MedicalOrderEntity savedOrder = medicalOrderRepository.save(entity);
                System.out.println("✅ Orden médica guardada (ID: " + savedOrder.getId() + ")");
                System.out.println("✅ Documento del paciente registrado: " + patientDocument);
                if (!hasContent) {
                    System.out.println("⚠️ La orden médica está vacía. Agregue elementos antes de guardar.");
                    return;
                }
                // 2. Guardar prescripciones (medicamentos)
                // Validar unicidad de número de orden
                if (medicalOrderRepository.existsByOrderNumber(currentOrder.getOrderNumber())) {
                    System.out.println("⚠️ El número de orden médica ya existe. Debe ser único.");
                    return;
                }
                if (currentOrder.getPrescriptions() != null && !currentOrder.getPrescriptions().isEmpty()) {
                    for (Prescription prescription : currentOrder.getPrescriptions()) {
                        PrescriptionEntity prescriptionEntity = new PrescriptionEntity();
                        prescriptionEntity.setMedicineId(prescription.getMedicineId());
                        prescriptionEntity.setDose(prescription.getDose());
                        prescriptionEntity.setDuration(prescription.getDuration());
                        prescriptionEntity.setItem(prescription.getItem());
                        prescriptionEntity.setMedicalOrder(savedOrder);
                        prescriptionRepository.save(prescriptionEntity);
                    }
                    System.out.println("✅ " + currentOrder.getPrescriptions().size() + " medicamentos guardados");
                }

                // 3. Guardar procedimientos
                if (currentOrder.getProcedures() != null && !currentOrder.getProcedures().isEmpty()) {
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
                    }
                    System.out.println("✅ " + currentOrder.getProcedures().size() + " procedimientos guardados");
                }

                // 4. Guardar pruebas diagnósticas
                if (currentOrder.getDiagnosticAids() != null && !currentOrder.getDiagnosticAids().isEmpty()) {
                    for (DiagnosticAid aid : currentOrder.getDiagnosticAids()) {
                        DiagnosticTestEntity testEntity = new DiagnosticTestEntity();
                        testEntity.setTestId(aid.getDiagnosticId());

                        // Convertir item de String a int
                        try {
                            int itemNum = Integer.parseInt(aid.getItem());
                            testEntity.setItem(itemNum);
                        } catch (NumberFormatException e) {
                            testEntity.setItem(0); // Valor por defecto si no es numérico
                        }

                        testEntity.setMedicalOrder(savedOrder);
                        diagnosticTestRepository.save(testEntity);
                    }
                    System.out.println(
                            "✅ " + currentOrder.getDiagnosticAids().size() + " pruebas diagnósticas guardadas");
                }

                System.out.println("✅ Orden médica completa guardada correctamente en MySQL.");
            } catch (Exception e) {
                System.out.println("⚠️ Error al guardar la orden médica: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("❌ Orden médica cancelada por el usuario.");
        }

        currentOrder = null;
    }
}
