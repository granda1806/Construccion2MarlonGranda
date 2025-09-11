package app.application.usecases;

import java.util.Scanner;
import app.domain.model.VitalSignsRecord;
import app.domain.ports.Nurses;

public class VitalSignsService {
    private Scanner sc = new Scanner(System.in);
    private Nurses nurse = new Nurses();

    public void registerVitalSigns() {
        System.out.print("¿Cuántos pacientes desea registrar? ");
        int amount = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < amount; i++) {
            VitalSignsRecord vital = new VitalSignsRecord();

            System.out.println("\nRegistro de signos vitales " + (i + 1));
            
            System.out.print("Ingrese el documento del paciente: ");
            vital.setPatientId(sc.nextLine());

            System.out.print("Ingrese la presión arterial: ");
            vital.setBloodPressure(sc.nextLine());

            System.out.print("Ingrese la temperatura: ");
            vital.setTemperature(sc.nextLine());

            System.out.print("Ingrese el pulso: ");
            vital.setPulse(sc.nextLine());

            System.out.print("Ingrese el nivel de oxígeno en sangre: ");
            vital.setBloodOxygenLevel(sc.nextLine());

            nurse.addVital(vital);
            System.out.println("✔ Paciente registrado con signos vitales.");
        }
    }

    public void searchPatient() {
        System.out.print("\nIngrese un ID de paciente para buscar: ");
        String searchedId = sc.nextLine();

        VitalSignsRecord found = nurse.findById(searchedId);
        if (found != null) {
            System.out.println("\nPaciente encontrado:");
            found.showData();
        } else {
            System.out.println("\nNo existe un paciente con ese ID.");
        }
    }
}