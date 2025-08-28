
package app.adapter.in.client;

import java.util.Scanner;

public class HResourcesClient {
    private static final String MENU = "Ingrese una opcion: \n 1. Crear Usuario \n 2. Actualizar datos personales \n 3. Eliminar usuario \n 4. Salir.";
    private static Scanner reader = new Scanner(System.in);
    
    public void session() {
        boolean session = true;
        while(session){
            session = menu();
        }
    }
    
    private boolean menu(){
        try{
        System.out.println(MENU);
        String option = reader.nextLine();
        switch (option) {
            case "1": {
            break;
            }
            case "2": {
            break;
            }
            case "3": {
            break;
            }
            case "4": {
                System.out.println("Cerrando sesion.");
                return false;
            } default: {
                System.out.println("Ingrese una opcion valida.");
                return true;
            }
        }
        
    } catch(Exception e){
        System.out.println(e.getMessage());
        return true;
        }
        return false;
    }
}
