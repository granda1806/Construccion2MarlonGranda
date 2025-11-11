package app.adapter.in.client;

import app.application.usecases.ViewPatientInfoUseCase;
import app.application.usecases.RegisterClinicalHistoryUseCase;
import app.application.usecases.UpdateClinicalHistoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
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
=======
/**
 * Cliente de consola para los médicos. Permite visualizar pacientes, registrar
 * y actualizar historias clínicas.
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
                case "1" ->
                    viewPatientInfoUseCase.execute();
                case "2" ->
                    registerClinicalHistoryUseCase.execute();
                case "3" ->
                    updateClinicalHistoryUseCase.execute();
                case "4" -> {
                    System.out.println("Saliendo del módulo médico...");
                    return false;
                }
                default ->
                    System.out.println("⚠️ Opción inválida. Intente de nuevo.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar la acción: " + e.getMessage());
        }
        return true;
    }
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
}
