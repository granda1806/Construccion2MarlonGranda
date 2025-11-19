package app.application.usecases;

import app.domain.model.Invoice;
import app.domain.model.Patient;
import app.domain.model.Person;
import app.domain.model.Policy;
import app.domain.ports.InvoicePort;

public class CreateInvoiceUseCase {

    private final InvoicePort invoicePort;

    public CreateInvoiceUseCase(InvoicePort invoicePort) {
        this.invoicePort = invoicePort;
    }

    /**
     * Genera la factura según las reglas de negocio:
     * - Si la póliza está activa: copago $50.000, resto a aseguradora.
     * - Si el paciente supera $1.000.000 en copagos en el año, no paga más copago.
     * - Si la póliza está inactiva o no existe, el paciente paga el total.
     */
    public Invoice execute(Patient patient, Person doctor, Policy policy, double totalAmount) throws Exception {
        if (patient == null)
            throw new Exception("El paciente no puede ser nulo");
        if (doctor == null)
            throw new Exception("El doctor no puede ser nulo");
        if (totalAmount <= 0)
            throw new Exception("El monto debe ser mayor que 0");

        boolean policyActive = policy != null && policy.isPolicyStatus();
        double copago = 0;
        double aseguradora = 0;

        // Simulación: aquí deberías consultar el total de copagos del paciente en el
        // año
        double copagoAcumuladoAnual = 0; // TODO: implementar consulta real

        if (policyActive) {
            // Si ya superó el millón en copagos este año
            if (copagoAcumuladoAnual >= 1_000_000) {
                copago = 0;
                aseguradora = totalAmount;
            } else {
                copago = Math.min(50000, totalAmount); // Si el total es menor a 50k, el copago es el total
                aseguradora = totalAmount - copago;
            }
        } else {
            // Sin póliza activa: el paciente paga todo
            copago = totalAmount;
            aseguradora = 0;
        }

        // Aquí podrías guardar el detalle de copago y aseguradora en la entidad Invoice
        // si lo deseas
        Invoice invoice = new Invoice(patient, doctor, policy, totalAmount);
        invoice.setCopay(copago);
        invoice.setInsuranceCovered(aseguradora);

        invoicePort.save(invoice);

        System.out.println("Factura guardada: " + invoice + ", Copago: $" + copago + ", Aseguradora: $" + aseguradora);

        return invoice;
    }
}
