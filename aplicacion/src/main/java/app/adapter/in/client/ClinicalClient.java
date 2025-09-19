
package app.adapter.in.client;

import app.adapter.in.builder.UserBuilder;
import app.application.usecases.ClinicalUseCase;
import app.domain.model.User;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClinicalClient {
    
    private static final String MENU = "Ingrese una opcion \n1. Crear Personal de clinica. \n2. Crear paciente. \n3. Crear orden";
    
    @Autowired
    private ClinicalUseCase clinicalUseCase;
    @Autowired
    private UserBuilder userBuilder;
    
    private static Scanner reader = new Scanner(System.in);
    
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
                return options(option);

        } catch (Exception e) {
                System.out.println(e.getMessage());
                return true;
        }
    }
    
    private boolean options(String option) throws Exception {
        switch (option) {
        case "1": {
                
                return true;
        }
        case "2": {

                return true;
        }
        case "3": {

                return true;
        }
        case "4": {

                return true;
        }
        case "5": {

                return true;
        }
        case "6": {

                return true;
        }
        case "7": {

                return false;
        }
        default: {
                System.out.println("Ingrese una opcion valida");
                return true;
        }
     }
}
}
