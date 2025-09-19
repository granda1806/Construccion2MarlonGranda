package app.adapter.in.client;

import java.util.Scanner;
import app.application.usecases.HResourcesUseCase;
import app.domain.model.User;
import app.adapter.in.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HResourcesClient
{
    
    private static final String MENU = "Ingrese una opcion: \n" +
            
                                       "1. Crear administrador \n" +
                                       "2. Crear soporte de informacion \n" +
                                       "3. Crear enfermera \n" +
                                       "4. Crear Doctor. \n" +
                                       "5. Crear Recuersos Humanos. \n"+
                                       "6. Salir.";
    
    private static Scanner reader = new Scanner(System.in);
    private HResourcesUseCase resourcesUseCase;
    @Autowired
    private UserBuilder userBuilder;
    
    public void session() {
        
        boolean session = true;
        
        while(session)
        {
            session = menu();
        }
        
    }
    
    private boolean menu()
    {
        
        try
        {
            
            System.out.println(MENU);
            String option = reader.nextLine();
            
            switch (option)
            {

                case "1":
                {
                    User user = readInfoFromUser();
                    resourcesUseCase.createAdmin(user);
                    return true;
                }

                case "2":
                {
                    User user = readInfoFromUser();
                    resourcesUseCase.createSupport(user);
                    return true;
                }

                case "3":
                {
                    User user = readInfoFromUser();
                    resourcesUseCase.createNurse(user);
                    return true;
                }

                case "4":
                {
                    User user = readInfoFromUser();
                    resourcesUseCase.createDoctor(user);
                    return true;
                }

                case "5":
                {
                    User user = readInfoFromUser();
                    resourcesUseCase.createHResources(user);
                    return true;
                }

                case "6":
                {
                    System.out.println("Cerrando sesion.");
                    return false;
                }

                default:
                {
                    System.out.println("Ingrese una opcion valida.");
                    return true;
                }

            }
        
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return true;
        }
        
    }
    
    private User readInfoFromUser() throws Exception
    {
        
        System.out.println("Ingrese nombre: ");
        String name = reader.nextLine();
        System.out.println("Ingrese cedula: ");
        String document = reader.nextLine();
        System.out.println("Ingrese la edad: ");
        String age = reader.nextLine();
        System.out.println("Ingrese usuario: ");
        String userName = reader.nextLine();
        System.out.println("Ingrese Contraseña: ");
        String password = reader.nextLine();
        return userBuilder.buildHResources(name, document, age, userName, password);
        
    }
    
}
