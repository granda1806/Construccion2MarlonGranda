
package app.application.usecases;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;

@Component
public class NursesMenu {

    private final Scanner reader = new Scanner(System.in);
    private final SearchPatientUseCase searchPatientUseCase;
    private final RegisterOrderAdministrationUseCase registerOrderUseCase;
    private final RegisterVitalSignsUseCase registerVitalSignsUseCase;
    private final SearchMedicalOrderUseCase searchOrderUseCase;

    @Autowired
    public NursesMenu(SearchPatientUseCase searchPatientUseCase,
                     RegisterOrderAdministrationUseCase registerOrderUseCase,
                     RegisterVitalSignsUseCase registerVitalSignsUseCase,
                     SearchMedicalOrderUseCase searchOrderUseCase) {
        this.searchPatientUseCase = searchPatientUseCase;
        this.registerOrderUseCase = registerOrderUseCase;
        this.registerVitalSignsUseCase = registerVitalSignsUseCase;
        this.searchOrderUseCase = searchOrderUseCase;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== MENU ENFERMERAS =====");
            System.out.println("1. Buscar paciente");
            System.out.println("2. Registrar administración de orden");
            System.out.println("3. Registrar signos vitales");
            System.out.println("4. Buscar orden médica");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" -> searchPatientUseCase.execute();
                case "2" -> registerOrderUseCase.execute();
                case "3" -> registerVitalSignsUseCase.execute();
                case "4" -> searchOrderUseCase.execute();
                case "5" -> running = false;
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
