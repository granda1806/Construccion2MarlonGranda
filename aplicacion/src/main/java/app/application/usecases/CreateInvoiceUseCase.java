package app.application.usecases;

import app.domain.model.Invoice;
import app.domain.model.Person;
import app.domain.model.Policy;

public class CreateInvoiceUseCase
{

    public Invoice execute(Person patient, Person doctor, Policy policy, double amount)
    {
        
        Invoice invoice = new Invoice(patient, doctor, policy, amount);
        System.out.println("Factura creada: " + invoice);
        return invoice;
        
    }
    
}
