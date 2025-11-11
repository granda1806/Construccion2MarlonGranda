
package app.application.usecases;

import app.infrastructure.persistence.entities.MedicalOrderEntity;
import app.infrastructure.persistence.repository.MedicalOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service // Esto hace que Spring la detecte como un bean
public class SearchMedicalOrderUseCase {

    private final MedicalOrderRepository orderRepository;
    private final Scanner reader = new Scanner(System.in);

    public SearchMedicalOrderUseCase(MedicalOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute() {
        System.out.print("\nIngrese numero de orden medica: ");
        String orderNum = reader.nextLine();

        MedicalOrderEntity order = orderRepository.findByOrderNumber(orderNum);
        if (order == null) {
            System.out.println("No se encontro la orden " + orderNum);
        } else {
            System.out.println("\nOrden medica encontrada:");
            System.out.println("Numero de orden: " + order.getOrderNumber());
            System.out.println("Observaciones: " + order.getObservations());
            if (order.getPatient() != null) {
                System.out.println("Paciente: " + order.getPatient().getName());
            }
        }
    }
}
