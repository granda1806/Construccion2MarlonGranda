package app.adapter.in.client;

import app.application.usecases.AuthUseCase;
import app.domain.model.User;
import app.domain.model.enums.Role;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class LoginClient {

<<<<<<< HEAD
    private final Scanner reader = new Scanner(System.in);

=======
    private static final String MENU = "Ingrese una opcion: \n"
            + "1. Recursos Humanos \n"
            + "2. Administrador \n"
            + "3. Doctor \n"
            + "4. Enfermera \n"
            + "5. salir. \n";

    private static Scanner reader = new Scanner(System.in);
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
    @Autowired
    private AuthUseCase authUseCase;

    @Autowired
<<<<<<< HEAD
    private HResourcesClient hResourcesClient;

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private NursesClient nursesClient;

    @Autowired
    private PasswordEncoder passwordEncoder; // 🔹 BCrypt inyectado

    public void logInProcess() {

        try {
            System.out.print("\nIngrese el nombre de usuario: ");
            String userName = reader.nextLine();
            System.out.print("\n");

            System.out.print("Ingrese la contraseña: ");
            String password = reader.nextLine();
            System.out.print("\n");

            Role role;

            // 🔹 Usuario administrador local
            if ("Admin".equals(userName) && "','".equals(password)) {
                System.out.println("\nAutenticación exitosa.\n");
                System.out.println("Usuario creado localmente únicamente para uso administrativo.");
                role = Role.HRESOURCES;
            } else {
                // 🔹 Busca el usuario en BD
                User user = authUseCase.findByUserName(userName);
                if (user == null) {
                    System.out.println("Usuario no encontrado.");
                    return;
=======
    private HResourcesClient clientHR;
    @Autowired
    private DoctorClient clientDoctor;
    @Autowired
    private NursesClient clientNurses;

    public void session() {

        boolean session = true;
        while (session) {
            session = menu();
        }

    }

    private boolean menu() {
        try {
            System.out.println(MENU);
            String option = reader.nextLine();

            switch (option) {
                case "1":
                    clientHR.session();
                    return true;

                case "2":
                    clientAdmin.session();
                    return true;

                case "3":
                    clientDoctor.session();
                    return true;

                case "4":
                    clientNurses.session();
                    return true;

                case "5":
                    System.out.println("Saliendo del sistema...");
                    return false;

                default: {
                    System.out.println("Ingrese una opcion valida.");
                    return true;
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
                }

                // 🔹 Verifica contraseña codificada con BCrypt
                if (!passwordEncoder.matches(password, user.getPassword())) {
                    System.out.println("Contraseña incorrecta.");
                    return;
                }

                System.out.println("\nAutenticación exitosa.\n");
                role = user.getRole();
            }

            // 🔹 Redirección según el rol
            switch (role) {
                case HRESOURCES -> hResourcesClient.session();
                case ADMIN -> adminClient.session();
                case DOCTOR -> doctorClient.session();
                case NURSE -> nursesClient.session();
                default -> System.out.println("Rol no reconocido: " + role);
            }

        } catch (Exception e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }

    public void session() {

        boolean running = true;

        while (running) {
            try {
                System.out.println("\n === ÁREA DE AUTENTICACIÓN === \n");
                System.out.println("1. Iniciar sesión.");
                System.out.println("2. Salir del programa.\n");

                System.out.print("Seleccione una opción: ");
                int option = reader.nextInt();
                reader.nextLine();

                switch (option) {
                    case 1 -> logInProcess();
                    case 2 -> {
                        System.out.println("Sesión finalizada.");
                        System.exit(0);
                    }
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                reader.nextLine();
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
<<<<<<< HEAD
=======
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
        }
    }
}
