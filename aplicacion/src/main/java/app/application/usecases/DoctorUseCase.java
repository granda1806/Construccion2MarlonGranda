package app.application.usecases;

import app.domain.model.*;
import java.util.Scanner;

public class DoctorUseCase {
    private final Scanner reader = new Scanner(System.in);

    public void searchMedicalHistory() {
        System.out.print("\nIngrese el ID del paciente: ");
        String patientId = reader.nextLine();

        // ⚡ Aquí deberías traer la historia clínica real desde un repositorio
        System.out.println("\nHistoria clinica encontrada para el paciente con ID: " + patientId);

        boolean submenu = true;
        while (submenu) {
            System.out.println("\nHISTORIA CLINICA.");
            System.out.println("1. Agregar informacion de la consulta");
            System.out.println("2. Recetar medicamentos");
            System.out.println("3. Procedimiento medico");
            System.out.println("4. Ayuda diagnostica");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            String option = reader.nextLine();

            switch (option) {
                case "1" -> agregarInfoConsulta();
                case "2" -> recetarMedicamentos();
                case "3" -> procedimientoMedico();
                case "4" -> ayudaDiagnostica();
                case "5" -> submenu = false;
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private void agregarInfoConsulta() {
        System.out.println("\nAGREGAR INFORMACION DE LA CONSULTA");
        HistoryRecord record = new HistoryRecord();
        System.out.print("Fecha: "); record.setDate(reader.nextLine());
        System.out.print("Cedula del medico: "); record.setDoctorId(reader.nextLine());
        System.out.print("Motivo: "); record.setReason(reader.nextLine());
        System.out.print("Sintomatologia: "); record.setSymptoms(reader.nextLine());
        System.out.print("Diagnostico: "); record.setDiagnosis(reader.nextLine());
        System.out.println("Consulta registrada.");
    }

    private void recetarMedicamentos() {
        System.out.println("\nRECETAR MEDICAMENTOS");
        Prescription prescription = new Prescription();
        System.out.print("Numero de orden: "); prescription.setOrderNumber(reader.nextLine());
        System.out.print("ID medicamento: "); prescription.setMedicineId(reader.nextLine());
        System.out.print("Dosis: "); prescription.setDose(reader.nextLine());
        System.out.print("Duracion: "); prescription.setDuration(reader.nextLine());
        System.out.print("Item: "); prescription.setItem(reader.nextLine());
        System.out.println("Medicamento agregado.");
    }

    private void procedimientoMedico() {
        System.out.println("\nPROCEDIMIENTO MEDICO");
        Procedure procedure = new Procedure();
        System.out.print("Numero de orden (6 dígitos): "); procedure.setOrderNumber(reader.nextLine());
        System.out.print("ID procedimiento: "); procedure.setProcedureId(reader.nextLine());
        System.out.print("Cantidad: "); procedure.setQuantity(reader.nextLine());
        System.out.print("Frecuencia: "); procedure.setFrequency(reader.nextLine());
        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine();
        if (req.equalsIgnoreCase("si")) {
            System.out.print("ID especialista: "); procedure.setSpecialistId(reader.nextLine());
        }
        System.out.print("Item: "); procedure.setItem(reader.nextLine());
        System.out.println("Procedimiento registrado.");
    }

    private void ayudaDiagnostica() {
        System.out.println("\nAYUDA DIAGNOSTICA");
        DiagnosticAid aid = new DiagnosticAid();
        System.out.print("Numero de orden: "); aid.setOrderNumber(reader.nextLine());
        System.out.print("ID ayuda diagnostica: "); aid.setDiagnosticId(reader.nextLine());
        System.out.print("Cantidad: "); aid.setQuantity(reader.nextLine());
        System.out.print("¿Requiere especialista? (si/no): ");
        String req = reader.nextLine();
        if (req.equalsIgnoreCase("si")) {
            System.out.print("ID especialista: "); aid.setSpecialistId(reader.nextLine());
        }
        System.out.print("Item: "); aid.setItem(reader.nextLine());
        System.out.println("Ayuda diagnostica registrada.");
    }
}
