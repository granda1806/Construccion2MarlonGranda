package app.adapter.in.client;

import java.util.InputMismatchException;
import java.util.Scanner;
import app.application.usecases.HResourcesUseCase;
import app.domain.model.User;
import app.adapter.in.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HResourcesClient
{

    @Autowired
    private HResourcesUseCase resourcesUseCase;

    @Autowired
    private UserBuilder userBuilder;

    private Scanner reader = new Scanner(System.in);

    public boolean session()
    {

        boolean session = true;

        while (session)
        {
            
            try
            {
                System.out.print(
                                "\nIngreso Recursos Humanos.\n" +
                                        
                                "1. Crear administrador\n" +
                                "2. Crear soporte de información\n" +
                                "3. Crear enfermera\n" +
                                "4. Crear doctor\n" +
                                "5. Crear recursos humanos\n" +
                                "6. Volver al menú principal\n\n" +
                                        
                                "Ingrese una opción: "
                );

                int menu = reader.nextInt();
                reader.nextLine();

                switch (menu)
                {
                    case 1:
                        resourcesUseCase.createAdmin(readInfoFromUser());
                        break;
                        
                    case 2:
                        resourcesUseCase.createSupport(readInfoFromUser());
                        break;
                        
                    case 3:
                        resourcesUseCase.createNurse(readInfoFromUser());
                        break;
                        
                    case 4:
                        resourcesUseCase.createDoctor(readInfoFromUser());
                        break;
                        
                    case 5:
                        resourcesUseCase.createHResources(readInfoFromUser());
                        break;
                        
                    case 6:
                        System.out.println("Retroceso al menú principal.");
                        session = false;
                        break;
                        
                    default:
                        System.out.println("Ingrese una opción válida.");
                        break;
                        
                }

            }
            catch (InputMismatchException e)
            {
                
                System.out.println("\nIngrese por favor un valor numérico válido.\n");
                reader.nextLine();
                
            }
            catch (Exception e)
            {
                
                System.out.println("Error: " + e.getMessage());
                
            }
            
        }

        return false;
        
    }

    private User readInfoFromUser() throws Exception
    {

        System.out.println("Ingrese nombre: ");
        String nameComplete = reader.nextLine();

        System.out.println("Ingrese apellido: ");
        String lastnameComplete = reader.nextLine();

        System.out.println("Ingrese cédula: ");
        String document = reader.nextLine();

        System.out.println("Ingrese la edad: ");
        String age = reader.nextLine();

        // Crear usuario automáticamente (genera usuario y contraseña)
        User user = userBuilder.buildHResources
        (
                nameComplete,
                lastnameComplete,
                document,
                age
        );

        System.out.println("\nUsuario creado exitosamente:");
        System.out.println("Usuario: " + user.getUserName());
        System.out.println("Contraseña generada: " + user.getPassword());

        return user;
        
    }
    
}