package app.domain.ports;

import app.domain.model.Invoice;

public interface InvoicePort {

    Invoice findById(Long id) throws Exception;

    void save(Invoice invoice) throws Exception;
}
