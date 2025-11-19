package app.application.usecases;

import app.infrastructure.persistence.entities.*;
import app.infrastructure.persistence.repository.*;
import java.util.Optional;
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
        Long document = Long.valueOf(reader.nextLine());

        Optional<PatientEntity> patientOpt = patientRepository.findByDocument(document);

        if (patientOpt.isEmpty()) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        PatientEntity patient = patientOpt.get();

        System.out.print("Ingrese número de orden médica: ");
        String orderNum = reader.nextLine();

        java.util.List<MedicalOrderEntity> orders = orderRepository.findByOrderNumber(orderNum);
        if (orders == null || orders.isEmpty()) {
            System.out.println("Orden médica no encontrada.");
            return;
        }

        MedicalOrderEntity order;
        if (orders.size() > 1) {
            System.out.println("⚠️ Atención: se encontraron " + orders.size()
                    + " órdenes con el mismo número.");
            order = SelectOrderHelper.chooseOrder(orders, reader);
            if (order == null)
                return;
        } else {
            order = orders.get(0);
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
