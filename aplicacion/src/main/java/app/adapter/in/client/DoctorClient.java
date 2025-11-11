package app.adapter.in.client;

import app.application.usecases.ViewPatientInfoUseCase;
import app.application.usecases.RegisterClinicalHistoryUseCase;
import app.application.usecases.UpdateClinicalHistoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

/**
 * Cliente de consola para los médicos.
 * Permite visualizar pacientes, registrar y actualizar historias clínicas.
 */
@Controller
public class DoctorClient {

    private static final Scanner reader = new Scanner(System.in);

    private final ViewPatientInfoUseCase viewPatientInfoUseCase;
    private final RegisterClinicalHistoryUseCase registerClinicalHistoryUseCase;
    private final UpdateClinicalHistoryUseCase updateClinicalHistoryUseCase;

    @Autowired
    public DoctorClient(ViewPatientInfoUseCase viewPatientInfoUseCase,
                        RegisterClinicalHistoryUseCase registerClinicalHistoryUseCase,
                        UpdateClinicalHistoryUseCase updateClinicalHistoryUseCase) {
        this.viewPatientInfoUseCase = viewPatientInfoUseCase;
        this.registerClinicalHistoryUseCase = registerClinicalHistoryUseCase;
        this.updateClinicalHistoryUseCase = updateClinicalHistoryUseCase;
    }

    public void start() {
        session();
    }

    public void session() {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }

    private boolean menu() {
        System.out.println("\n===== MENÚ MÉDICO =====");
        System.out.println("1. Buscar paciente");
        System.out.println("2. Registrar historia clínica");
        System.out.println("3. Actualizar historia clínica");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");

        String option = reader.nextLine();

        try {
            switch (option) {
                case "1" -> viewPatientInfoUseCase.execute();
                case "2" -> registerClinicalHistoryUseCase.execute();
                case "3" -> updateClinicalHistoryUseCase.execute();
                case "4" -> {
                    System.out.println("Saliendo del módulo médico...");
                    return false;
                }
                default -> System.out.println("⚠️ Opción inválida. Intente de nuevo.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar la acción: " + e.getMessage());
        }
        return true;
    }
}
