package app.application.usecases;

import app.domain.model.*;
import java.util.Scanner;

public class DoctorUseCase
{
    
    private final Scanner reader = new Scanner(System.in);
    private MedicalOrder currentOrder;

    public void searchMedicalHistory()
    {
        
        System.out.print("\nIngrese el ID del paciente: ");
        String patientId = reader.nextLine();

        System.out.println("\nHistoria clínica encontrada para el paciente con ID: " + patientId);

        boolean alternatemenu  = true;
        
        while (alternatemenu)
        {
            
            System.out.println("\nSUBMENU HISTORIA CLINICA");
            System.out.println("1. Agregar informacion de la consulta");
            System.out.println("2. Recetar medicamentos");
            System.out.println("3. Procedimiento medico");
            System.out.println("4. Ayuda diagnostica");
            System.out.println("5. Crear orden medica");
            System.out.println("6. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            String option = reader.nextLine();

            switch (option)
            {
                case "1" -> consultationInformation();
                case "2" -> alternatemenu();
                case "3" -> medicalProcedure();
                case "4" -> diagnosticAssistance();
                case "5" -> createMedicalOrder();
                case "6" -> alternatemenu = false;
                default -> System.out.println("Opcion no valida.");
            }
            
        }
        
    }

    private void consultationInformation()
    {
        
        System.out.println("\nAGREGAR INFORMACION DE LA CONSULTA");
        HistoryRecord record = new HistoryRecord();
        System.out.print("Fecha: "); record.setDate(reader.nextLine());
        System.out.print("Cedula del medico: "); record.setDoctorId(reader.nextLine());
        System.out.print("Motivo: "); record.setReason(reader.nextLine());
        System.out.print("Sintomatologia: "); record.setSymptoms(reader.nextLine());
        System.out.print("Diagnostico: "); record.setDiagnosis(reader.nextLine());
        System.out.println("Consulta registrada.");
        
    }

    private void alternatemenu()
    {
        
        if (currentOrder == null)
        {
            
            currentOrder = new MedicalOrder();
            System.out.print("Ingrese numero de orden medica: ");
            currentOrder.setOrderNumber(reader.nextLine());
            
        }

        Prescription prescription = new Prescription();
        System.out.print("ID medicamento: "); prescription.setMedicineId(reader.nextLine());
        System.out.print("Dosis: "); prescription.setDose(reader.nextLine());
        System.out.print("Duracion: "); prescription.setDuration(reader.nextLine());
        System.out.print("Item: "); prescription.setItem(reader.nextLine());

        currentOrder.addPrescription(prescription);
        System.out.println("Medicamento agregado a la orden.");
        
    }

    private void medicalProcedure()
    {
        
        if (currentOrder == null)
        {
            
            currentOrder = new MedicalOrder();
            System.out.print("Ingrese numero de orden médica: ");
            currentOrder.setOrderNumber(reader.nextLine());
            
        }

        Procedure procedure = new Procedure();
        System.out.print("ID procedimiento: "); procedure.setProcedureId(reader.nextLine());
        System.out.print("Cantidad: "); procedure.setQuantity(reader.nextLine());
        System.out.print("Frecuencia: "); procedure.setFrequency(reader.nextLine());
        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine();
        
        if (req.equalsIgnoreCase("si"))
        {
        
            System.out.print("ID especialista: "); procedure.setSpecialistId(reader.nextLine());
            
        }
        
        System.out.print("Item: "); procedure.setItem(reader.nextLine());

        currentOrder.addProcedure(procedure);
        System.out.println("Procedimiento agregado a la orden.");
        
    }

    private void diagnosticAssistance()
    {
        
        if (currentOrder == null)
        {
            
            currentOrder = new MedicalOrder();
            System.out.print("Ingrese numero de orden medica: ");
            currentOrder.setOrderNumber(reader.nextLine());
            
        }

        DiagnosticAid aid = new DiagnosticAid();
        System.out.print("ID ayuda diagnostica (examen): "); aid.setDiagnosticId(reader.nextLine());
        System.out.print("Cantidad: "); aid.setQuantity(reader.nextLine());
        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine();
        
        if (req.equalsIgnoreCase("si"))
        {
            
            System.out.print("ID especialista: "); aid.setSpecialistId(reader.nextLine());
            
        }
        
        System.out.print("Item: "); aid.setItem(reader.nextLine());

        currentOrder.addDiagnosticAid(aid);
        System.out.println("Ayuda diagnostica agregada a la orden.");
        
    }

    private void createMedicalOrder()
    {
        
        if (currentOrder == null)
        {
            
            System.out.println("No hay datos cargados en la orden.");
            return;
            
        }

        System.out.println("\nORDEN MEDICA FINALIZADA:");
        System.out.println(currentOrder);

        // Espacio para persistir la orden en un repositorio
        currentOrder = null; // Vacía la instancia de registros para crear una nueva orden
        
    }
    
}
