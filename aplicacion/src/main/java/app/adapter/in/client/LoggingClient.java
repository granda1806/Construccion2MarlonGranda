package app.adapter.in.client;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;

import app.application.usecases.AuthUseCase;
import app.domain.model.User;
import app.domain.model.enums.Role;

@Controller
public class LoggingClient {

    private final Scanner reader = new Scanner(System.in);

    private final long sessionLimit = 3 * 60 * 1000; // Three minutes in milliseconds
    private long StartTime;
    private String userName, password, token;
    private Role role = null;

    @Autowired
    private AuthUseCase authUseCase;

    @Autowired
    private HResourcesClient HRC;

    @Autowired
    private HResourcesClient hResourcesClient;

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private NursesClient nursesClient;

    public void validateRole() {

        User user = authUseCase.findByUserName(userName);

        if (user == null) {

            System.out.println("Usuario no encontrado en la base de datos.");
            return;

        }

        role = user.getRole();

    }

    public void StartSession() {

        System.out.println("\n==============================");
        System.out.println("     ENTORNO DE ACCESO SEGURO");
        System.out.println("==============================");

        StartTime = System.currentTimeMillis();

        boolean running = true;

        while (running) {

            try {

                System.out.println("\nMENÚ PRINCIPAL\n");

                System.out.println("1. Iniciar sesión.");
                System.out.println("2. Salir del sistema.");

                System.out.print("Seleccione una opción: ");
                int option = reader.nextInt();

                reader.nextLine(); // Here the first memory buffer cleanup is performed.

                switch (option) {

                    case 1 -> LoggingProcess();

                    case 2 -> {
                        System.out.println("Saliendo del sistema...");
                        running = false;
                    }

                    default -> System.out.println("Opción no válida. Intente de nuevo.");

                }

            } catch (InputMismatchException e) {

                System.out.println("Por favor, ingrese un número válido.");
                reader.nextLine(); // Here the second memory buffer cleanup is performed.

            } catch (Exception e) {

                System.out.println("Error inesperado: " + e.getMessage());

            }

        }

        reader.close();

        System.exit(0);

    }

    public void LoggingProcess() {

        try {
            // =======================
            // VALIDACIÓN DE USUARIO
            // =======================

            int userAttempts = 0;

            boolean userFound = false;
            boolean passwordCorrect = false;

            while (userAttempts < 3) {

                System.out.print("\nIngrese el nombre de usuario: ");
                userName = reader.nextLine();

                boolean isAdmin = userName.equals("Admin");

                if (isAdmin) {

                    userFound = true;

                    role = Role.ADMIN;

                    break;

                }

                if (authUseCase.userExists(userName)) {

                    userFound = true;

                    validateRole();

                    break;

                } else {

                    userAttempts++;

                    System.out.println("Usuario no encontrado. Intentos restantes: " + (3 - userAttempts));

                    if (userAttempts == 3) {

                        System.out.println("\nHa excedido los intentos permitidos.");

                        System.out.println("Regresando al menú principal...");

                        return;

                    }

                }

            }

            if (!userFound)
                return;

            // ==========================
            // VALIDACIÓN DE CONTRASEÑA
            // ==========================

            int attempts = 0;

            while (attempts < 3) {

                System.out.print("Ingrese la contraseña: ");
                password = reader.next();
                reader.nextLine(); // Limpiar buffer

                final boolean isAdmin = userName.equals("Admin");
                final boolean adminPasswordCorrect = password.equals("','");

                if (isAdmin && adminPasswordCorrect) {

                    System.out.println("\nInicio de sesión del ADMIN exitoso.");

                    HRC.session();

                    return;

                }

                if (authUseCase.passwordIsCorrect(userName, password)) {

                    passwordCorrect = true;

                    break;

                }

                attempts++;

                System.out.println("Contraseña incorrecta. Intentos restantes: " + (3 - attempts));

                if (attempts == 3) {

                    System.out.println("\nHa excedido los intentos permitidos.");

                    System.out.println("Regresando al menú principal...");

                    return;

                }

            }

            if (!passwordCorrect)
                return;

            // ==========================
            // VALIDACIÓN DE TOKEN
            // ==========================

            long now = System.currentTimeMillis();

            // SI NO → generar uno nuevo (pero NO mostrarlo)
            String generatedToken = authUseCase.generateTokenForUser(userName);

            if (generatedToken == null) {
                System.out.println("\nNo fue posible generar un token. Inténtelo más tarde.");
                return;
            }

            // Ahora pedimos el token manualmente al usuario
            System.out.print("Ingrese el token de autenticación: ");
            token = reader.nextLine();

            // Validar token
            if (!authUseCase.tokenIsValid(userName, token)) {
                System.out.println("\nToken incorrecto.");
                System.out.println("Debe esperar 1 hora para volver a intentarlo.");
                return;
            }

            // ¿YA TIENE un token activo?
            if (authUseCase.userHasActiveToken(userName)) {
                System.out.println("\nYa tiene un token activo.");
                System.out.println("Debe esperar 1 hora para generar uno nuevo.");
                return;
            }

            // Si el token es correcto → eliminarlo
            authUseCase.consumeToken(userName);

            System.out.println("\nInicio de sesión exitoso.");

            switch (role) {

                case HRESOURCES -> hResourcesClient.session();
                case ADMIN -> adminClient.session();
                case DOCTOR -> doctorClient.session();
                case NURSE -> nursesClient.session();
                default -> System.out.println(" Rol no reconocido: " + role);

            }

        } catch (Exception e) {

            System.out.println("Error al iniciar sesión: " + e.getMessage());

        }

    }

}