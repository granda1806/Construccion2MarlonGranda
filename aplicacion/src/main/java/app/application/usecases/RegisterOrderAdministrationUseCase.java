package app.application.usecases;

import app.infrastructure.persistence.entities.*;
import app.infrastructure.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class RegisterOrderAdministrationUseCase {

    private final MedicalOrderRepository orderRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final ProcedureRepository procedureRepository;
    private final DiagnosticTestRepository diagnosticTestRepository;
    private final app.infrastructure.persistence.repository.MedicationRepository medicationRepository;
    private final Scanner reader = new Scanner(System.in);

    // Inyección de dependencias por constructor
    @Autowired
    public RegisterOrderAdministrationUseCase(
            MedicalOrderRepository orderRepository,
            PrescriptionRepository prescriptionRepository,
            ProcedureRepository procedureRepository,
            DiagnosticTestRepository diagnosticTestRepository,
            app.infrastructure.persistence.repository.MedicationRepository medicationRepository) {
        this.orderRepository = orderRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.procedureRepository = procedureRepository;
        this.diagnosticTestRepository = diagnosticTestRepository;
        this.medicationRepository = medicationRepository;
    }

    public void execute() {
        System.out.print("\nIngrese numero de orden medica: ");
        String orderNum = reader.nextLine();

        java.util.List<app.infrastructure.persistence.entities.MedicalOrderEntity> orders = orderRepository
                .findByOrderNumber(orderNum);

        if (orders == null || orders.isEmpty()) {
            System.out.println("No se encontro la orden " + orderNum);
            return;
        }

        MedicalOrderEntity order;
        if (orders.size() > 1) {
            System.out.println("⚠️ Atención: se encontraron " + orders.size()
                    + " órdenes con el mismo número.");
            order = SelectOrderHelper.chooseOrder(orders, reader);
            if (order == null)
                return; // usuario canceló o id inválido
        } else {
            order = orders.get(0);
        }

        System.out.println("\nOrden medica encontrada: " + order.getOrderNumber());

        boolean submenu = true;
        while (submenu) {
            System.out.println("\nSUBMENU ADMINISTRACION DE ORDEN");
            System.out.println("1. Registrar medicamento administrado");
            System.out.println("2. Registrar procedimiento realizado");
            System.out.println("3. Registrar prueba diagnóstica realizada");
            System.out.println("4. Registrar observaciones");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" -> registerMedication(order);
                case "2" -> registerProcedure(order);
                case "3" -> registerDiagnosticTest(order);
                case "4" -> registerObservation(order);
                case "5" -> submenu = false;
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private void registerMedication(MedicalOrderEntity order) {
        System.out.print("Ingrese ID del medicamento: ");
        String medId = reader.nextLine();
        System.out.print("Ingrese dosis: ");
        String dose = reader.nextLine();
        System.out.print("Ingrese duración del tratamiento: ");
        String duration = reader.nextLine();
        System.out.print("Ingrese número de ítem: ");
        String item = reader.nextLine();

        try {
            int itemNum = Integer.parseInt(item);

            // 💾 Crear y guardar prescripción
            PrescriptionEntity prescription = new PrescriptionEntity();
            prescription.setMedicineId(medId);
            prescription.setDose(dose);
            prescription.setDuration(duration);
            prescription.setItem(itemNum);
            prescription.setMedicalOrder(order);

            prescriptionRepository.save(prescription);
            // Mostrar nombre y costo del medicamento si está disponible
            String medInfo = medId;
            try {
                long medIdLong = Long.parseLong(medId);
                var medOpt = medicationRepository.findById(medIdLong);
                if (medOpt.isPresent()) {
                    var med = medOpt.get();
                    medInfo = med.getName() + " (ID: " + med.getId() + ") - Costo: $" + med.getCost();
                }
            } catch (NumberFormatException ignored) {
            } catch (Exception ignored) {
            }

            System.out.println("✅ Medicamento registrado y guardado en BD: " + medInfo + " - Dosis: " + dose);
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: El ítem debe ser un número.");
        }
    }

    private void registerProcedure(MedicalOrderEntity order) {
        System.out.print("Ingrese ID del procedimiento: ");
        String procId = reader.nextLine();
        System.out.print("Ingrese cantidad: ");
        String quantity = reader.nextLine();
        System.out.print("Ingrese frecuencia: ");
        String frequency = reader.nextLine();
        System.out.print("Ingrese costo: ");
        String cost = reader.nextLine();
        System.out.print("¿Requiere especialista? (si/no): ");
        String requiresSpec = reader.nextLine();
        System.out.print("Ingrese número de ítem: ");
        String item = reader.nextLine();

        try {
            int quantityNum = Integer.parseInt(quantity);
            double costNum = Double.parseDouble(cost);
            int itemNum = Integer.parseInt(item);
            boolean requiresSpecialist = requiresSpec.equalsIgnoreCase("si");

            // 💾 Crear y guardar procedimiento
            ProcedureEntity procedure = new ProcedureEntity();
            procedure.setProcedureId(procId);
            procedure.setQuantity(quantityNum);
            procedure.setFrequency(frequency);
            procedure.setCost(costNum);
            procedure.setRequiresSpecialist(requiresSpecialist);
            procedure.setMedicalOrder(order);

            procedureRepository.save(procedure);
            System.out.println("✅ Procedimiento registrado y guardado en BD: " + procId);
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese valores numéricos válidos.");
        }
    }

    private void registerDiagnosticTest(MedicalOrderEntity order) {
        System.out.print("Ingrese ID de la prueba diagnóstica: ");
        String testId = reader.nextLine();
        System.out.print("Ingrese número de ítem: ");
        String item = reader.nextLine();

        try {
            int itemNum = Integer.parseInt(item);

            // 💾 Crear y guardar prueba diagnóstica
            DiagnosticTestEntity diagnosticTest = new DiagnosticTestEntity();
            diagnosticTest.setTestId(testId);
            diagnosticTest.setItem(itemNum);
            diagnosticTest.setMedicalOrder(order);

            diagnosticTestRepository.save(diagnosticTest);
            System.out.println("✅ Prueba diagnóstica registrada y guardada en BD: " + testId);
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: El ítem debe ser un número.");
        }
    }

    private void registerObservation(MedicalOrderEntity order) {
        System.out.print("Ingrese observaciones: ");
        String obs = reader.nextLine();

        order.setObservations(obs);
        orderRepository.save(order); // 💾 Guarda los cambios en la base de datos
        System.out.println("🗒️ Observación registrada y guardada en la base de datos.");
    }
}
