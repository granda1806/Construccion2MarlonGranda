package app.adapter.in.client;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginClient {

    private static final String MENU = "Ingrese una opcion: \n"
            + "1. Recursos Humanos \n"
            + "2. Administrador \n"
            + "3. Doctor \n"
            + "4. Enfermera \n"
            + "5. salir. \n";

    private static Scanner reader = new Scanner(System.in);
    @Autowired
    private AdminClient clientAdmin;
    @Autowired
    private HResourcesClient clientHR;
    @Autowired
    private DoctorClient clientDoctor;
    @Autowired
    private NursesClient clientNurses;

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

            switch (option) {
                case "1":
                    clientHR.session();
                    return true;

                case "2":
                    clientAdmin.session();
                    return true;

                case "3":
                    clientDoctor.session();
                    return true;

                case "4":
                    clientNurses.session();
                    return true;

                case "5":
                    System.out.println("Saliendo del sistema...");
                    return false;

                default: {
                    System.out.println("Ingrese una opcion valida.");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
