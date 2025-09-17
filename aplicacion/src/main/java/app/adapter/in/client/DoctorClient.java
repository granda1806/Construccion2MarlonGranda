package app.adapter.in.client;

import app.application.usecases.DoctorUseCase;
import java.util.Scanner;

public class DoctorClient
{
    private static final Scanner reader = new Scanner(System.in);
    private final DoctorUseCase doctorUseCase = new DoctorUseCase();

    public void session()
    {
        
        boolean session = true;
        
        while (session)
        {
            
            System.out.println("\nMENU MEDICO ");
            System.out.println("1. Buscar historia clinica.");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opcion: ");
            String option = reader.nextLine();

            switch (option)
            {
                
                case "1" -> doctorUseCase.searchMedicalHistory();
                case "2" ->
                {
                    
                    System.out.println("Cerrando sesion del medico...");
                    session = false;
                    
                }
                
                default -> System.out.println("Opcion no valida, intente nuevamente.");
                
            }
            
        }
        
    }
    
}