package app.domain.services;

import app.domain.model.Policy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class BillingService {

    public void generateInvoice(String patientName, int patientAge, String patientId, String doctorName, Policy policy,
            double totalServiceCost, double yearlyCopay) {

        System.out.println("Facturación");
        System.out.println("Nombre del paciente: " + patientName);
        System.out.println("Edad: " + patientAge);
        System.out.println("Cédula: " + patientId);
        System.out.println("Nombre del médico tratante: " + doctorName);
        System.out.println("Nombre de la compañía de seguro: " + policy.getInsuranceCompanyName());
        System.out.println("Número de póliza: " + policy.getPolicyNumber());

        long daysValidity = ChronoUnit.DAYS.between(LocalDate.now(), policy.getEndDate());
        System.out.println("Días de vigencia de la póliza: " + daysValidity);
        System.out.println("Fecha de finalización de la póliza: " + policy.getEndDate());

        double copay = 50000; // Copago estándar
        double amountToInsurer = totalServiceCost - copay;

        if (!policy.isActive()) {
            System.out.println("La póliza está inactiva. El paciente debe pagar el total de los servicios.");
            System.out.println("Total a pagar: $" + totalServiceCost);
        } else if (yearlyCopay > 1000000) {
            System.out.println(
                    "El paciente ha superado el límite de copago anual. La aseguradora cubrirá el costo total.");
            System.out.println("Total a pagar por la aseguradora: $" + totalServiceCost);
        } else {
            System.out.println("Detalle del cobro:");
            System.out.println("Copago del paciente: $" + copay);
            System.out.println("Monto a cobrar a la aseguradora: $" + amountToInsurer);
        }
    }
}