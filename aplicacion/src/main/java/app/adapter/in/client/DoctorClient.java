package app.adapter.in.client;

import app.application.usecases.ViewPatientInfoUseCase;
import app.application.usecases.RegisterClinicalHistoryUseCase;
import app.application.usecases.UpdateClinicalHistoryUseCase;
import app.application.usecases.DoctorUseCase;
import app.application.usecases.CreateMedicalOrderUseCase;
import app.application.usecases.SearchMedicalOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

/**
 * Cliente de consola para los médicos.
 * Permite visualizar pacientes, registrar y actualizar historias clínicas, y
 * crear órdenes médicas.
 */
@Controller
public class DoctorClient {

    private static final Scanner reader = new Scanner(System.in);

    private final ViewPatientInfoUseCase viewPatientInfoUseCase;
    private final RegisterClinicalHistoryUseCase registerClinicalHistoryUseCase;
    private final UpdateClinicalHistoryUseCase updateClinicalHistoryUseCase;
    private final DoctorUseCase doctorUseCase;
    private final CreateMedicalOrderUseCase createMedicalOrderUseCase;
    private final SearchMedicalOrderUseCase searchMedicalOrderUseCase;

    @Autowired
    public DoctorClient(ViewPatientInfoUseCase viewPatientInfoUseCase,
            RegisterClinicalHistoryUseCase registerClinicalHistoryUseCase,
            UpdateClinicalHistoryUseCase updateClinicalHistoryUseCase,
            DoctorUseCase doctorUseCase,
            CreateMedicalOrderUseCase createMedicalOrderUseCase,
            SearchMedicalOrderUseCase searchMedicalOrderUseCase) {
        this.viewPatientInfoUseCase = viewPatientInfoUseCase;
        this.registerClinicalHistoryUseCase = registerClinicalHistoryUseCase;
        this.updateClinicalHistoryUseCase = updateClinicalHistoryUseCase;
        this.doctorUseCase = doctorUseCase;
        this.createMedicalOrderUseCase = createMedicalOrderUseCase;
        this.searchMedicalOrderUseCase = searchMedicalOrderUseCase;
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
        System.out.println("4. Crear/Gestionar órdenes médicas");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");

        String option = reader.nextLine();

        try {
            switch (option) {
                case "1" -> viewPatientInfoUseCase.execute();
                case "2" -> registerClinicalHistoryUseCase.execute();
                case "3" -> updateClinicalHistoryUseCase.execute();
                case "4" -> medicalOrderMenu();
                case "5" -> {
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

    private void medicalOrderMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n===== ÓRDENES MÉDICAS =====");
            System.out.println("1. Crear nueva orden médica");
            System.out.println("2. Buscar orden médica existente");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            String option = reader.nextLine().trim();

            switch (option) {
                case "1" -> createMedicalOrderUseCase.execute();
                case "2" -> searchMedicalOrderUseCase.execute();
                case "3" -> {
                    System.out.println("↩️ Volviendo al menú principal...");
                    inMenu = false;
                }
                default -> System.out.println("⚠️ Opción no válida.");
            }
        }
    }
}
