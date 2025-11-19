package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

}
