package app.application.usecases;

import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.repository.MedicalOrderRepository;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class RegisterOrderAdministrationUseCase {

    private final MedicalOrderRepository orderRepository;
    private final Scanner reader = new Scanner(System.in);

    // Inyección de dependencias por constructor
    public RegisterOrderAdministrationUseCase(MedicalOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute() {
        System.out.print("\nIngrese numero de orden medica: ");
        String orderNum = reader.nextLine();

        MedicalOrderEntity order = orderRepository.findByOrderNumber(orderNum);

        if (order == null) {
            System.out.println("No se encontro la orden " + orderNum);
            return;
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
        System.out.print("Ingrese item asociado: ");
        String item = reader.nextLine();
        System.out.println("Medicamento administrado: " + medId + " (Item " + item + ")");
    }

    private void registerProcedure(MedicalOrderEntity order) {
        System.out.print("Ingrese ID del procedimiento: ");
        String procId = reader.nextLine();
        System.out.print("Ingrese item asociado: ");
        String item = reader.nextLine();
        System.out.println("Procedimiento realizado: " + procId + " (Item " + item + ")");
    }

    private void registerDiagnosticTest(MedicalOrderEntity order) {
        System.out.print("Ingrese ID de la prueba diagnóstica: ");
        String testId = reader.nextLine();
        System.out.print("Ingrese ítem asociado: ");
        String item = reader.nextLine();
        System.out.println("🧪 Prueba diagnóstica realizada: " + testId + " (Item " + item + ")");
    }

    private void registerObservation(MedicalOrderEntity order) {
        System.out.print("Ingrese observaciones: ");
        String obs = reader.nextLine();

        order.setObservations(obs);
        orderRepository.save(order); // 💾 Guarda los cambios en la base de datos
        System.out.println("🗒️ Observación registrada y guardada en la base de datos.");
    }
}
