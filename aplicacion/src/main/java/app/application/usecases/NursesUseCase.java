package app.application.usecases;

import app.domain.model.*;
import java.util.*;

public class NursesUseCase {

    private final Scanner reader = new Scanner(System.in);

    // Simulación de bases de datos en memoria
    private static final Map<Long, Patient> patients = new HashMap<>();
    private static final Map<String, MedicalOrder> orders = new HashMap<>();

    public static void addPatient(Patient person) {
        patients.put(person.getDocument(), person);
    }

    public static void addOrder(MedicalOrder order) {
        orders.put(order.getOrderNumber(), order);
    }

    public void searchForPatient() {
        System.out.print("\nIngrese la cedula del paciente: ");
        long idCard = Long.parseLong(reader.nextLine());

        Patient patient = patients.get(idCard);
        if (patient == null) {
            System.out.println("No se encontro el paciente con cedula: " + idCard);
            return;
        }

        System.out.println("\nInformacion del paciente");
        System.out.println("Nombre: " + patient.getNameComplete());
        System.out.println("Documento: " + patient.getDocument());
        System.out.println("Edad: " + patient.getAge());
        System.out.println("Genero: " + patient.getGender());
        System.out.println("Direccion: " + patient.getAddress());
        System.out.println("Telefono: " + patient.getPhoneNumber());
        System.out.println("Email: " + patient.getEmail());
        System.out.println("Contacto emergencia: " + patient.getEmergencyContactName()
                + " (" + patient.getRelationshipPatient() + "), Tel: "
                + patient.getEmergencyContactNumber());
    }

    public void registerOrderAdministration() {
        System.out.print("\nIngrese numero de orden medica: ");
        String orderNum = reader.nextLine();

        MedicalOrder order = orders.get(orderNum);
        if (order == null) {
            System.out.println("No se encontro la orden " + orderNum);
            return;
        }

        boolean submenu = true;
        while (submenu) {
            System.out.println("\nSUBMENU ADMINISTRACION ORDEN");
            System.out.println("1. Registrar medicamento administrado");
            System.out.println("2. Registrar procedimiento realizado");
            System.out.println("3. Registrar prueba diagnostica realizada");
            System.out.println("4. Registrar observaciones");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opcion: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Ingrese ID del medicamento: ");
                    String medId = reader.nextLine();
                    System.out.print("Ingrese item asociado: ");
                    String item = reader.nextLine();
                    System.out.println("Medicamento administrado: " + medId + " (Item " + item + ")");
                }
                case "2" -> {
                    System.out.print("Ingrese ID del procedimiento: ");
                    String procId = reader.nextLine();
                    System.out.print("Ingrese item asociado: ");
                    String item = reader.nextLine();
                    System.out.println("Procedimiento realizado: " + procId + " (Item " + item + ")");
                }
                case "3" -> {
                    System.out.print("Ingrese ID de la prueba diagnostica: ");
                    String testId = reader.nextLine();
                    System.out.print("Ingrese item asociado: ");
                    String item = reader.nextLine();
                    System.out.println("Prueba diagnostica realizada: " + testId + " (Item " + item + ")");
                }
                case "4" -> {
                    System.out.print("Ingrese observaciones: ");
                    String obs = reader.nextLine();
                    System.out.println("Observacion registrada: " + obs);
                }
                case "5" ->
                    submenu = false;
                default ->
                    System.out.println("Opcion invalida.");
            }
        }
    }

    public void registerVitalSigns() {
        System.out.print("\nIngrese la cedula del paciente: ");
        long idCard = Long.parseLong(reader.nextLine());

        Person patient = patients.get(idCard);
        if (patient == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        VitalSignsRecord signos = new VitalSignsRecord();
        signos.setPatientId(String.valueOf(patient.getDocument())); // 🔹 Ahora sí guardamos el ID

        System.out.print("Presion arterial: ");
        signos.setBloodPressure(reader.nextLine());
        System.out.print("Temperatura: ");
        signos.setTemperature(reader.nextLine());
        System.out.print("Pulso: ");
        signos.setPulse(reader.nextLine());
        System.out.print("Nivel de oxigeno: ");
        signos.setBloodOxygenLevel(reader.nextLine());

        System.out.println("\nSignos vitales registrados para " + patient.getNameComplete());
        signos.showData();

        // Asociar orden médica existente
        System.out.print("\n¿Desea asociar a una orden medica existente? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            System.out.print("Ingrese numero de orden: ");
            String orderNum = reader.nextLine();
            MedicalOrder order = orders.get(orderNum);

            if (order != null) {
                System.out.println("Orden encontrada: " + order);
                System.out.println("Registro de administracion realizado.");
            } else {
                System.out.println("No se encontro la orden.");
                
            }
            
        }

        // Observaciones
        System.out.print("\n¿Desea registrar observaciones adicionales? (si/no): ");
        if (reader.nextLine().equalsIgnoreCase("si")) {
            Observation obs = new Observation();
            System.out.print("Escriba observaciones: ");
            obs.setNotes(reader.nextLine());
            
            System.out.println("Observaciones guardadas: " + obs);
            
        }
        
    }

    public void searchForAMedicalOrder() {
        System.out.print("\nIngrese numero de orden medica: ");
        String orderNum = reader.nextLine();

        MedicalOrder order = orders.get(orderNum);
        if (order == null) {
            System.out.println("No se encontro la orden " + orderNum);
        } else {
            System.out.println("\nOrden medica encontrada:");
            System.out.println(order);
 
        } 
        
    }
    
}