package app.adapter.in.client;

import app.application.usecases.DoctorUseCase;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorClient {

    @Autowired
    private DoctorUseCase doctorUseCase;

    private final Scanner reader = new Scanner(System.in);

    public void start() {
        doctorUseCase.manageMedicalHistory();
    }

    public void session() {

        boolean session = true;

        while (session) {

            System.out.println("\nMENU MEDICO ");
            System.out.println("1. Añadir expediente del paciente.");
            System.out.println("2. Crear registro médico");
            System.out.println("3. Actualizar registro médico");
            System.out.println("4. Menú anterior");

            System.out.print("Seleccione una opción: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" -> doctorUseCase.manageMedicalHistory();
                case "2" -> doctorUseCase.createMedicalRecord();
                case "3" -> doctorUseCase.updateMedicalRecord();
                case "4" -> {
                    System.out.println("\nDe vuelta al menú principal");
                    session = false;
                }
                default -> System.out.println("Opción no válida, intente nuevamente.");
            }
        }
    }
}
