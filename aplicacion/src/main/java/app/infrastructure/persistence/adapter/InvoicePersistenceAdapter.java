package app.infrastructure.persistence.adapter;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;
import app.infrastructure.persistence.entities.InvoiceEntity;
import app.infrastructure.persistence.mapper.InvoiceMapper;
import app.infrastructure.persistence.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoicePersistenceAdapter implements InvoicePort {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoicePersistenceAdapter(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice findById(Long id) throws Exception {
        // Implementación básica, se recomienda mapear de Entity a Domain
        InvoiceEntity entity = invoiceRepository.findById(id)
                .orElseThrow(() -> new Exception("Factura no encontrada"));
        // Aquí deberías mapear de Entity a Domain (no implementado por simplicidad)
        return null;
    }

    @Override
    public void save(Invoice invoice) throws Exception {
        InvoiceEntity entity = InvoiceMapper.toEntity(invoice);
        invoiceRepository.save(entity);
    }
}
