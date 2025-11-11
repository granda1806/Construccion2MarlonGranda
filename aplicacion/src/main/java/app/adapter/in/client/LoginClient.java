
package app.adapter.in.client;

import app.application.usecases.AuthUseCase;
import app.domain.model.User;
import app.domain.model.enums.Role;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

/**
 * Cliente de consola para autenticación de usuarios.
 * Permite el inicio de sesión y redirige al módulo correspondiente según el rol.
 */
@Controller
public class LoginClient {

    private final Scanner reader = new Scanner(System.in);

    private static final String MENU = """
            Ingrese una opción:
            1. Recursos Humanos
            2. Administrador
            3. Doctor
            4. Enfermera
            5. Salir
            """;

    @Autowired
    private AuthUseCase authUseCase;

    @Autowired
    private HResourcesClient hResourcesClient;

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private NursesClient nursesClient;

    @Autowired
    private PasswordEncoder passwordEncoder; // BCrypt inyectado

    /**
     * Proceso de autenticación de usuario.
     */
    public void logInProcess() {
        try {
            System.out.print("\nIngrese el nombre de usuario: ");
            String userName = reader.nextLine();

            System.out.print("Ingrese la contraseña: ");
            String password = reader.nextLine();

            Role role;

            // 🔹 Usuario administrador local (solo para uso administrativo)
            if ("Admin".equalsIgnoreCase(userName) && "','".equals(password)) {
                System.out.println("\nAutenticación exitosa.");
                System.out.println("Usuario creado localmente únicamente para uso administrativo.");
                role = Role.HRESOURCES;
            } else {
                // 🔹 Busca el usuario en la base de datos
                User user = authUseCase.findByUserName(userName);

                if (user == null) {
                    System.out.println("❌ Usuario no encontrado.");
                    return;
                }

                // 🔹 Verifica la contraseña (BCrypt)
                if (!passwordEncoder.matches(password, user.getPassword())) {
                    System.out.println("❌ Contraseña incorrecta.");
                    return;
                }

                System.out.println("\n✅ Autenticación exitosa.\n");
                role = user.getRole();
            }

            // 🔹 Redirección según el rol
            switch (role) {
                case HRESOURCES -> hResourcesClient.session();
                case ADMIN -> adminClient.session();
                case DOCTOR -> doctorClient.session();
                case NURSE -> nursesClient.session();
                default -> System.out.println("⚠️ Rol no reconocido: " + role);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al iniciar sesión: " + e.getMessage());
        }
    }

    /**
     * Menú principal del cliente de inicio de sesión.
     */
    public void session() {
        boolean running = true;

        while (running) {
            try {
                System.out.println("\n=== ÁREA DE AUTENTICACIÓN ===");
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Salir del programa");
                System.out.print("\nSeleccione una opción: ");

                int option = reader.nextInt();
                reader.nextLine(); // limpia el buffer

                switch (option) {
                    case 1 -> logInProcess();
                    case 2 -> {
                        System.out.println("👋 Sesión finalizada.");
                        running = false;
                    }
                    default -> System.out.println("⚠️ Opción no válida. Intente de nuevo.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                reader.nextLine();
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
}
