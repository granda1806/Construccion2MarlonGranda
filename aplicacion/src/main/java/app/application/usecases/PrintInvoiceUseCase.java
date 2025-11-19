package app.application.usecases;

import app.domain.model.Invoice;
import java.text.SimpleDateFormat;

public class PrintInvoiceUseCase {
        public String print(Invoice invoice) {
                if (invoice == null)
                        return "Factura no encontrada";
                StringBuilder sb = new StringBuilder();
                sb.append("\n================ FACTURA ================\n");
                sb.append("Paciente: ")
                                .append(invoice.getPatient() != null ? invoice.getPatient().getNameComplete() : "N/A")
                                .append("\n");
                sb.append("Edad: ").append(invoice.getPatient() != null ? invoice.getPatient().getAge() : "N/A")
                                .append("\n");
                sb.append("Cédula: ").append(invoice.getPatient() != null ? invoice.getPatient().getDocument() : "N/A")
                                .append("\n");
                sb.append("Médico tratante: ")
                                .append(invoice.getDoctor() != null ? invoice.getDoctor().getNameComplete() : "N/A")
                                .append("\n");
                sb.append("Compañía de seguro: ")
                                .append(invoice.getPolicy() != null && invoice.getPolicy().getPolicyName() != null
                                                ? invoice.getPolicy().getPolicyName().name()
                                                : "N/A")
                                .append("\n");
                sb.append("Número de póliza: ")
                                .append(invoice.getPolicy() != null ? invoice.getPolicy().getPolicyNumber() : "N/A")
                                .append("\n");
                // Fecha de inicio y fin de la póliza
                String fechaInicio = "N/A";
                String fechaFin = "N/A";
                if (invoice.getPolicy() != null) {
                        java.sql.Date fin = invoice.getPolicy().getPolicyTerminationDate();
                        if (fin != null) {
                                // Si hay fecha de finalización, la de inicio es 1 año antes
                                java.util.Calendar cal = java.util.Calendar.getInstance();
                                cal.setTime(fin);
                                cal.add(java.util.Calendar.YEAR, -1);
                                fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
                                fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(fin);
                        } else {
                                // Si no hay fecha, se asume hoy y hoy+1 año
                                java.util.Date hoy = new java.util.Date();
                                fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
                                java.util.Calendar cal = java.util.Calendar.getInstance();
                                cal.setTime(hoy);
                                cal.add(java.util.Calendar.YEAR, 1);
                                fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
                        }
                }
                sb.append("Fecha de inicio de la póliza: ").append(fechaInicio).append("\n");
                sb.append("Fecha de finalización de la póliza: ").append(fechaFin).append("\n");
                sb.append("\n--- Detalle de Facturación ---\n");
                sb.append("Total servicios: $").append(invoice.getAmount()).append("\n");
                sb.append("Copago paciente: $").append(invoice.getCopay()).append("\n");
                sb.append("Valor cubierto por aseguradora: $").append(invoice.getInsuranceCovered()).append("\n");
                sb.append("==========================================\n");
                return sb.toString();
        }
}
