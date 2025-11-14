package app.application.usecases;

import app.infrastructure.persistence.entities.*;
import app.infrastructure.persistence.repository.*;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class RegisterVitalSignsUseCase {

    private final Scanner reader = new Scanner(System.in);
    private final PatientRepository patientRepository;
    private final VitalSignsRepository vitalSignsRepository;
    private final MedicalOrderRepository orderRepository;

    public RegisterVitalSignsUseCase(PatientRepository patientRepository,
                                     VitalSignsRepository vitalSignsRepository,
                                     MedicalOrderRepository orderRepository) {
        this.patientRepository = patientRepository;
        this.vitalSignsRepository = vitalSignsRepository;
        this.orderRepository = orderRepository;
    }

    public void execute() {
        System.out.print("\nIngrese la cédula del paciente: ");
        long document = Long.parseLong(reader.nextLine());

        PatientEntity patient = patientRepository.findByDocument(document);

        if (patient == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        System.out.print("Ingrese número de orden médica: ");
        String orderNum = reader.nextLine();

        MedicalOrderEntity order = orderRepository.findByOrderNumber(orderNum);
        if (order == null) {
            System.out.println("Orden médica no encontrada.");
            return;
        }

        VitalSignsEntity signos = new VitalSignsEntity();
        System.out.print("Presión arterial: ");
        signos.setBloodPressure(reader.nextLine());

        System.out.print("Temperatura: ");
        double temperatura = Double.parseDouble(reader.nextLine());
        signos.setTemperature(temperatura);

        System.out.print("Pulso: ");
        int pulso = Integer.parseInt(reader.nextLine());
        signos.setPulse(pulso);

        System.out.print("Nivel de oxígeno: ");
        int oxigeno = Integer.parseInt(reader.nextLine());
        signos.setBloodOxygenLevel(oxigeno);

        signos.setMedicalOrder(order);
        vitalSignsRepository.save(signos);

        System.out.println("Signos vitales registrados correctamente para el paciente " + patient.getName());
    }
}
