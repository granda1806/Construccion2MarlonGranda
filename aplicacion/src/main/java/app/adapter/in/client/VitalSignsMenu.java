package app.adapter.in.client;

import java.util.Scanner;
import app.application.usecases.VitalSignsService;

public class VitalSignsMenu {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        VitalSignsService vitalSignsService = new VitalSignsService();
        
        boolean exit = false;
        
        while (!exit) {
            showVitalSignsMenu();
            int option = keyboard.nextInt();
            keyboard.nextLine();
            
            switch (option) {
                case 1 -> vitalSignsService.registerVitalSigns();
                case 2 -> vitalSignsService.searchPatient();
                case 3 -> {
                    System.out.println("Saliendo del sistema...");
                    exit = true;
                }
                default -> System.out.println("Opción no válida, intente nuevamente.");
            }
        }
        keyboard.close();
    }
    
    private static void showVitalSignsMenu() {
        System.out.println("\n*** Registro y búsqueda de pacientes ***");
        System.out.println("""
                1. Registrar signos vitales.
                2. Buscar paciente
                3. Salir
                """);
        System.out.print("Seleccione su opción: ");
    }
}