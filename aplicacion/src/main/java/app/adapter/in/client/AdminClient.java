
package app.adapter.in.client;

import app.adapter.in.builder.UserBuilder;
import app.application.usecases.AdminUseCase;
import java.util.Scanner;
import app.domain.model.User;


public class AdminClient {
    private static final String MENU = "Ingrese una opcion: \n 1. Crear paciente \n 2. Programar cita  \n 3. Facturacion  \n 4. Seguros medicos \n 5. Salir.";
    private static Scanner reader = new Scanner(System.in);
    private AdminUseCase adminUseCase;
    private UserBuilder userBuilder;
    
    public void session() {
        boolean session = true;
        while(session){
            session = menu();
        }
    }
    
    private boolean menu() {
        try{
            System.out.println(MENU);
            String option = reader.nextLine();
            switch(option){
                case "1": {
                    User user = readInfoFromUserAd();
                    adminUseCase.createPatient(user);
                return true;
                }
                case "2": {
                    System.out.println("En proceso...");
                return true;
                }
                case "3": {
                    System.out.println("En proceso...");
                return true;
                }
                case "4": {
                    System.out.println("En proceso...");
                    return true;
                }
                case "5": {
                    System.out.println("Cerrando sesion...");
                    return false;
                } default: {
                    System.out.println("Ingrese una opcion valida.");
                    return true;
                }
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return true;
        }
    }
    
    private User readinfoFromUserAd() throws Exception {
        System.out.println("Ingrese nombre: ");
        String name = reader.nextLine();
        System.out.println("Ingrese cedula: ");
        String document = reader.nextLine();
        System.out.println("Ingrese edad: ");
        String age = reader.nextLine();
        System.out.println("Ingrese fecha de nacimiento: ");
        String date = reader.nextLine();
        System.out.println("Ingrese genero: ");
        String gender = reader.nextLine();
        System.out.println("Ingrese direccion: ");
        String addres = reader.nextLine();
        System.out.println("Datos de contacto.");
        System.out.println("Ingrese nombre contacto de emergencia: ");
        String contactName = reader.nextLine();
        System.out.println("Ingrese que relacion tiene con el paciente: ");
        String relationship = reader.nextLine();
        System.out.println("Ingrese numero contacto de emergencia: ");
        String contactNumber = reader.nextLine();
        return userBuilder.buildAdmin(name, document, age, date, gender, addres, contactName, relationship, contactNumber);
    }    
}
