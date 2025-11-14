
package app.adapter.in.client;

import app.adapter.in.builder.PolicyBuilder;
import app.application.usecases.PolicyUseCase;
import app.domain.model.Policy;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PolicyClient {
     private static final String MENU = "Ingrese una opcion: \n" +
            
                                       "1. Crear ARL Sura \n" +
                                       "2. Crear Positiva Seguros \n" +
                                       "3. Crear EPS Sura \n" +
                                       "4. Crear EPS Sanitas. \n" +
                                       "5. crear Coomeva. \n"+
                                       "6. Regresar al menu anterior. \n";
    
    private static Scanner reader = new Scanner(System.in);
    
    @Autowired
    private PolicyUseCase policyUseCase;
    @Autowired
    private PolicyBuilder policyBuilder;
    
    public void session() {
        
        boolean session = true;
        while(session)
        {
            session = menu();
        }
        
    }
    
    private boolean menu(){
        
        try
        {
            
            System.out.println(MENU);
            String option = reader.nextLine();
            
            switch (option)
            {

                case "1":
                {
                    Policy policy = readInfoFromPolicy();
                    policyUseCase.createARLSura(policy);
                    return true;
                }

                case "2":
                {
                    Policy policy = readInfoFromPolicy();
                    policyUseCase.cratePositivaSeguros(policy);
                    return true;
                }

                case "3":
                {
                   Policy policy = readInfoFromPolicy();
                   policyUseCase.crateEPSSura(policy);
                    return true;
                }

                case "4":
                {
                   Policy policy = readInfoFromPolicy();
                   policyUseCase.crateEPSSanitas(policy);
                    return true;
                }

                case "5":
                {
                   Policy policy = readInfoFromPolicy();
                   policyUseCase.crateEPSSanitas(policy);
                    return true;
                }

                case "6":
                {
                    System.out.println("Salindo de seguros...");
                    return false;
                }

                default:
                {
                    System.out.println("Ingrese una opcion valida.");
                    return true;
                }

            }
        
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
            return true;
        }
    
    }
    
    private Policy readInfoFromPolicy() throws Exception {

        System.out.println("Ingrese documento de admin: ");
        String documentA = reader.nextLine();
        System.out.println("Ingrese documento de paciente: ");
        String documentP = reader.nextLine();
        System.out.println("Numero de poliza del paciente: ");
        String policyNumber = reader.nextLine();
        System.out.println("Estado de poliza: 1 - activa | 2 - inactiva. :");
        String policyStatus = reader.nextLine();

        return policyBuilder.policyBuilder(documentA, documentP, policyNumber,policyStatus);

    }
    
}
