package app.adapter.in.client;

import app.adapter.in.builder.AppointmentBuilder;
import app.adapter.in.builder.UserBuilder;
import app.application.usecases.AdminUseCase;
import app.domain.model.Appointment;
import app.domain.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminClient
{
    
    private static final String MENU = "Ingrese una opcion: \n" +
                                       " 1. Crear paciente \n" +
                                       " 2. Programar cita \n" +
                                       " 3. Facturacion \n" +
                                       " 4. Seguros medicos \n" +
                                       " 5. Regresar al menu principal.";
    
    private static Scanner reader = new Scanner(System.in);
    @Autowired
    private AdminUseCase adminUseCase;
    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private AppointmentBuilder appointmentBuilder;
    
    @Autowired
    private PolicyClient policyClient;
    
    public void session()
    {
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
            switch(option)
            {
                case "1":
                {
                    Patient patient = readInfoFromPatient();
                    adminUseCase.createPatient(patient);
                    return true;
                }
                
                case "2":
                {
                    Appointment appointment = readInfoFromAppointment();
                    adminUseCase.createAppointment(appointment);
                    return true;
                }
                
                case "3":
                {
                    System.out.println("En proceso...");
                    return true;
                }
                
                case "4":
                {

                    policyClient.session();
                    return true;
                }
                
                case "5":
                { 
                    System.out.println("Saliendo de Adminitrador...");
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
    
private Patient readInfoFromPatient() throws Exception {

    System.out.println("Ingrese nombre: ");
    String nameComplete = reader.nextLine();
    System.out.println("Ingrese apellido: ");
    String lastnameComplete = reader.nextLine();

    Long document = null;
    while (document == null) {
        System.out.println("Ingrese cedula: ");
        String docStr = reader.nextLine();
        try {
            document = Long.parseLong(docStr);
        } catch (NumberFormatException e) {
            System.out.println("Cédula inválida. Ingrese solo números, intente de nuevo.");
        }
    }

    Integer age = null;
    while (age == null) {
        System.out.println("Ingrese edad: ");
        String ageStr = reader.nextLine();
        try {
            age = Integer.parseInt(ageStr);
            if (age < 0 || age > 150) {
                System.out.println("Edad inválida");
                age = null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Edad inválida. Ingrese un número entero.");
        }
    }

    System.out.println("Ingrese genero: ");
    String gender = reader.nextLine();
    System.out.println("Ingrese direccion: ");
    String address = reader.nextLine();
    System.out.println("Datos de contacto.");
    System.out.println("Ingrese nombre contacto de emergencia: ");
    String contactName = reader.nextLine();
    System.out.println("Ingrese que relacion tiene con el paciente: ");
    String relationship = reader.nextLine();
    System.out.println("Ingrese numero contacto de emergencia: ");
    String contactNumber = reader.nextLine();

    return userBuilder.buildAdmin(
            nameComplete,
            lastnameComplete,
            document,
            age,
            gender,
            address,
            contactName,
            relationship,
            contactNumber
    );
}

private Appointment readInfoFromAppointment() throws Exception {
    System.out.println("Ingrese documento de admin: ");
    String documentAdmin = reader.nextLine();
    System.out.println("Ingrese documento de paciente: ");
    String documentPatient = reader.nextLine();
    
    return appointmentBuilder.appointmentBuilder(documentAdmin,documentPatient);
    }


}