package app.adapter.in.client;

import app.application.usecases.SearchPatientUseCase;
import app.application.usecases.RegisterVitalSignsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
import org.springframework.stereotype.Controller;

@Controller
public class NursesClient {

    private static final String MENU = "Ingrese una opción:\n"
            + "1. Buscar paciente\n"
            + "2. Registrar signos vitales\n"
            + "3. Salir al menu principal.";

    private static final Scanner reader = new Scanner(System.in);

    private final SearchPatientUseCase searchPatientUseCase;
    private final RegisterVitalSignsUseCase registerVitalSignsUseCase;

    @Autowired
    public NursesClient(SearchPatientUseCase searchPatientUseCase,
            RegisterVitalSignsUseCase registerVitalSignsUseCase) {
        this.searchPatientUseCase = searchPatientUseCase;
        this.registerVitalSignsUseCase = registerVitalSignsUseCase;
    }

    public void session() {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }

    private boolean menu() {
        try {
            System.out.println("\n===== MENU ENFERMERAS =====");
            System.out.println(MENU);
            System.out.print("Seleccione una opcion: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" -> {
                    searchPatientUseCase.execute();
                    return true;
                }
                case "2" -> {
                    registerVitalSignsUseCase.execute();
                    return true;
                }
                case "3" -> {
                    System.out.println("Saliendo del modulo de enfermería...");
                    return false;
                }
                default -> {
                    System.out.println("Ingrese una opcion valida.");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return true;
        }
    }
}
