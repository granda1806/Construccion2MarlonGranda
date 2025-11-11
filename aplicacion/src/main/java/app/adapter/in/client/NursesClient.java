package app.adapter.in.client;

import app.application.usecases.NursesUseCase;
import java.util.Scanner;
import org.springframework.stereotype.Controller;

@Controller
public class NursesClient {

    private static final Scanner reader = new Scanner(System.in);
    private final NursesUseCase nursesUseCase = new NursesUseCase();

    public void session() {
        boolean session = true;

        while (session) {
            System.out.println("\nMENU ENFERMERIA");
            System.out.println("1. Buscar paciente");
            System.out.println("2. Registrar signos vitales");
            System.out.println("3. Registrar administracion de ordenes");
            System.out.println("4. Buscar orden medica");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" ->
                    nursesUseCase.searchForPatient();
                case "2" ->
                    nursesUseCase.registerVitalSigns();
                case "3" ->
                    nursesUseCase.registerOrderAdministration();
                case "4" ->
                    nursesUseCase.searchForAMedicalOrder();
                case "5" -> {
                    System.out.println("Cerrando sesion de enfermeria...");
                    session = false;
                }
                default ->
                    System.out.println("Opcion invalida.");
            }
        }
    }
}
