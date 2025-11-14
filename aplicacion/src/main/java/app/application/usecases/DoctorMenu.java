package app.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

/**
 * Menú del módulo médico (Doctor). Interactúa directamente con los casos de uso
 * para gestionar historias clínicas.
 */
@Component
public class DoctorMenu {

    private final Scanner reader = new Scanner(System.in);
    private final ViewPatientInfoUseCase viewPatientInfoUseCase;
    private final RegisterClinicalHistoryUseCase registerClinicalHistoryUseCase;
    private final UpdateClinicalHistoryUseCase updateClinicalHistoryUseCase;

    @Autowired
    public DoctorMenu(ViewPatientInfoUseCase viewPatientInfoUseCase,
            RegisterClinicalHistoryUseCase registerClinicalHistoryUseCase,
            UpdateClinicalHistoryUseCase updateClinicalHistoryUseCase) {
        this.viewPatientInfoUseCase = viewPatientInfoUseCase;
        this.registerClinicalHistoryUseCase = registerClinicalHistoryUseCase;
        this.updateClinicalHistoryUseCase = updateClinicalHistoryUseCase;
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== MENÚ DOCTOR =====");
            System.out.println("1. Ver información del paciente");
            System.out.println("2. Registrar historia clínica");
            System.out.println("3. Actualizar historia clínica existente");
            System.out.println("4. Volver al menú principal");
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
                    case "4" ->
                        running = false;
                    default ->
                        System.out.println("⚠️ Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
