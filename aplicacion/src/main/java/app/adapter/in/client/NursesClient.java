package app.adapter.in.client;

import app.application.usecases.NursesUseCase;
import java.util.Scanner;

public class NursesClient {
    private static final Scanner reader = new Scanner(System.in);
    private final NursesUseCase nursesUseCase = new NursesUseCase();

    public void session() {
        boolean session = true;
        while (session) {
            System.out.println("\nMENU");
            System.out.println("1. Buscar paciente");
            System.out.println("2. Buscar orden medica");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" -> nursesUseCase.buscarPaciente();
                case "2" -> nursesUseCase.buscarOrdenMedica();
                case "3" -> {
                    System.out.println("Cerrando sesion de enfermeria...");
                    session = false;
                }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }
}
