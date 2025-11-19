
package app.adapter.in.client;

import app.application.usecases.SearchPatientUseCase;
import app.application.usecases.RegisterVitalSignsUseCase;
import app.application.usecases.ViewPatientInfoUseCase;
import app.application.usecases.RegisterOrderAdministrationUseCase;
import app.application.usecases.VisitUseCase;
import app.domain.model.Visit;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class NursesClient {

    private static final String MENU = "Ingrese una opción:\n"
            + "1. Buscar paciente\n"
            + "2. Registrar signos vitales\n"
            + "3. Ver información completa del paciente\n"
            + "4. Administrar orden (registrar medicamentos/procedimientos/pruebas/observaciones)\n"
            + "5. Registrar visita\n"
            + "6. Salir al menu principal.";

    private static final Scanner reader = new Scanner(System.in);

    private final SearchPatientUseCase searchPatientUseCase;
    private final RegisterVitalSignsUseCase registerVitalSignsUseCase;
    private final ViewPatientInfoUseCase viewPatientInfoUseCase;
    private final RegisterOrderAdministrationUseCase registerOrderAdministrationUseCase;
    private final VisitUseCase visitUseCase;

    @Autowired
    public NursesClient(SearchPatientUseCase searchPatientUseCase,
            RegisterVitalSignsUseCase registerVitalSignsUseCase,
            ViewPatientInfoUseCase viewPatientInfoUseCase,
            RegisterOrderAdministrationUseCase registerOrderAdministrationUseCase,
            VisitUseCase visitUseCase) {
        this.searchPatientUseCase = searchPatientUseCase;
        this.registerVitalSignsUseCase = registerVitalSignsUseCase;
        this.viewPatientInfoUseCase = viewPatientInfoUseCase;
        this.registerOrderAdministrationUseCase = registerOrderAdministrationUseCase;
        this.visitUseCase = visitUseCase;
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
                    viewPatientInfoUseCase.execute();
                    return true;
                }
                case "4" -> {
                    try {
                        registerOrderAdministrationUseCase.execute();
                    } catch (Exception e) {
                        System.out.println("Error administrando la orden: " + e.getMessage());
                    }
                    return true;
                }
                case "5" -> {
                    registerVisit();
                    return true;
                }
                case "6" -> {
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

    private void registerVisit() {
        try {
            System.out.println("\n=== Registro de visita ===");
            System.out.print("Ingrese ID del paciente (numero): ");
            String pid = reader.nextLine().trim();
            if (pid.isEmpty() || !pid.matches("\\d+")) {
                System.out.println("ID de paciente inválido.");
                return;
            }
            Long pacienteId = Long.parseLong(pid);

            System.out.print("Ingrese motivo de la visita: ");
            String motivo = reader.nextLine();

            System.out.print("Ingrese observaciones (opcional): ");
            String observaciones = reader.nextLine();

            Visit visit = new Visit();
            visit.setPacienteId(pacienteId);
            visit.setFechaVisita(LocalDateTime.now());
            visit.setMotivo(motivo);
            visit.setObservaciones(observaciones);

            try {
                Visit saved = visitUseCase.guardar(visit);
                System.out.println("Visita registrada (ID: "
                        + (saved != null && saved.getId() != null ? saved.getId() : "n/a") + ").");
            } catch (Exception ex) {
                System.out.println("Error guardando la visita: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error en registro de visita: " + e.getMessage());
        }
    }
}
