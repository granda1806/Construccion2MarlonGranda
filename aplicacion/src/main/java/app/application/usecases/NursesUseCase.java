package app.application.usecases;

import app.domain.model.*;
import java.util.*;

public class NursesUseCase
{
    
    private final Scanner reader = new Scanner(System.in);

    // Simulación de bases de datos en memoria
    private static final Map<Long, Person> pacientes = new HashMap<>();
    private static final Map<String, MedicalOrder> ordenes = new HashMap<>();

    // Método para registrar datos desde AdminClient y DoctorUseCase
    public static void addPaciente(Person person)
    {
        
        pacientes.put(person.getDocument(), person); // usamos getDocument() de Person
        
    }

    public static void addOrden(MedicalOrder orden)
    {
        
        ordenes.put(orden.getOrderNumber(), orden);
        
    }

    public void buscarPaciente()
    {
        
        System.out.print("\nIngrese la cedula del paciente: ");
        long cedula = Long.parseLong(reader.nextLine());

        Person paciente = pacientes.get(cedula);
        
        if (paciente == null)
        {
            
            System.out.println("No se encontró el paciente con cedula: " + cedula);
            return;
            
        }

        System.out.println("\nInformación del paciente:");
        System.out.println("Nombre: " + paciente.getName());
        System.out.println("Documento: " + paciente.getDocument());
        System.out.println("Edad: " + paciente.getAge());
        System.out.println("Genero: " + paciente.getGender());
        System.out.println("Direccion: " + paciente.getAddres());
        System.out.println("Telefono: " + paciente.getPhoneNumber());
        System.out.println("Email: " + paciente.getEmail());
        System.out.println("Contacto emergencia: " + paciente.getEmergencyContactName()
                           + " (" + paciente.getRelationshipPatient() + "), Tel: "
                           + paciente.getEmergencyContactNumber());

        System.out.print("\n¿Desea registrar signos vitales? (si/no): ");
        String respuesta = reader.nextLine();
        
        if (respuesta.equalsIgnoreCase("si"))
        {
            
            registrarSignosVitales(paciente);
            
        }
        
    }

    private void registrarSignosVitales(Person paciente)
    {
        
        VitalSignsRecord signos = new VitalSignsRecord();

        System.out.print("Presion arterial: ");
        signos.setBloodPressure(reader.nextLine());
        System.out.print("Temperatura: ");
        signos.setTemperature(reader.nextLine());
        System.out.print("Pulso: ");
        signos.setPulse(reader.nextLine());
        System.out.print("Nivel de oxigeno: ");
        signos.setBloodOxygenLevel(reader.nextLine());

        System.out.println("Signos vitales registrados para " + paciente.getName());

        // Registrar órdenes médicas asociadas
        System.out.print("\n¿Desea asociar a una orden medica existente? (si/no): ");
        
        if (reader.nextLine().equalsIgnoreCase("si"))
        {
            System.out.print("Ingrese numero de orden: ");
            String ordenNum = reader.nextLine();
            MedicalOrder orden = ordenes.get(ordenNum);
            
            if (orden != null)
            {
                
                System.out.println("Orden encontrada: " + orden);
                System.out.println("Registro de administracion realizado.");
                
            }
            else
            {
                
                System.out.println("No se encontro la orden.");
                
            }
            
        }

        System.out.print("\n¿Desea registrar observaciones adicionales? (si/no): ");
        
        if (reader.nextLine().equalsIgnoreCase("si"))
        {
            
            Observation obs = new Observation();
            System.out.print("Escriba observaciones: ");
            obs.setNotes(reader.nextLine());
            
            System.out.println("Observaciones guardadas: " + obs);
            
        }
        
    }

    public void buscarOrdenMedica()
    {
        
        System.out.print("\nIngrese numero de orden medica: ");
        String ordenNum = reader.nextLine();

        MedicalOrder orden = ordenes.get(ordenNum);
        
        if (orden == null)
        {
        
            System.out.println("No se encontro la orden " + ordenNum);
            
        }
        else
        {
            
            System.out.println("\n?Orden medica encontrada:");
            System.out.println(orden);
            
        }
        
    }
    
}
