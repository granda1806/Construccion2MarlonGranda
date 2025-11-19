package app.application.usecases;

import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.repository.MedicalOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service // Esto hace que Spring la detecte como un bean
public class SearchMedicalOrderUseCase {

    private final MedicalOrderRepository orderRepository;
    private final Scanner reader = new Scanner(System.in);

    public SearchMedicalOrderUseCase(MedicalOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute() {
        System.out.println("\n========== BÚSQUEDA DE ORDEN MÉDICA ==========");
        System.out.println("1. Buscar por número de orden");
        System.out.println("2. Buscar por documento del paciente");
        System.out.print("Seleccione una opción: ");
        String option = reader.nextLine().trim();

        switch (option) {
            case "1":
                searchByOrderNumber();
                break;
            case "2":
                searchByPatientDocument();
                break;
            default:
                System.out.println("❌ Opción no válida");
        }
    }

    private void searchByOrderNumber() {
        System.out.print("\nIngrese número de orden médica: ");
        String orderNum = reader.nextLine().trim();

        java.util.List<MedicalOrderEntity> orders = orderRepository.findByOrderNumber(orderNum);
        if (orders == null || orders.isEmpty()) {
            System.out.println("❌ No se encontró la orden " + orderNum);
            return;
        }

        MedicalOrderEntity order;
        if (orders.size() > 1) {
            System.out.println("⚠️ Se encontraron " + orders.size() + " órdenes con ese número.");
            order = SelectOrderHelper.chooseOrder(orders, reader);
            if (order == null)
                return;
        } else {
            order = orders.get(0);
        }

        displayOrderDetails(order);
    }

    private void searchByPatientDocument() {
        System.out.print("\nIngrese documento del paciente: ");
        try {
            Long patientDocument = Long.parseLong(reader.nextLine().trim());

            List<MedicalOrderEntity> orders = orderRepository.findByPatientDocument(patientDocument);
            if (orders == null || orders.isEmpty()) {
                System.out.println(
                        "❌ No se encontraron órdenes médicas para el paciente con documento: " + patientDocument);
            } else {
                System.out.println("\n✅ Se encontraron " + orders.size() + " orden(es) médica(s):");
                for (int i = 0; i < orders.size(); i++) {
                    System.out.println("\n--- ORDEN " + (i + 1) + " ---");
                    displayOrderDetails(orders.get(i));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Documento inválido. Ingrese un número válido.");
        }
    }

    private void displayOrderDetails(MedicalOrderEntity order) {
        System.out.println("\n✅ Orden médica encontrada:");
        System.out.println("Número de orden: " + order.getOrderNumber());
        System.out.println("Documento del paciente: " + order.getPatientDocument());
        System.out.println("Observaciones: " + order.getObservations());
        System.out.println("Fecha de creación: " + order.getCreatedAt());
        if (order.getPatient() != null) {
            System.out.println("Nombre del paciente: " + order.getPatient().getName());
        }
        if (order.getMedicalHistory() != null) {
            System.out.println("Historia clínica ID: " + order.getMedicalHistory().getId());
        }
    }
}
