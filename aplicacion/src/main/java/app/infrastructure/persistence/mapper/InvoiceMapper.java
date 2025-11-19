package app.infrastructure.persistence.mapper;

import app.domain.model.Invoice;
import app.infrastructure.persistence.entities.InvoiceEntity;
import java.sql.Date;

public class InvoiceMapper {
    public static InvoiceEntity toEntity(Invoice invoice) {
        InvoiceEntity entity = new InvoiceEntity();
        if (invoice.getPatient() != null) {
            entity.setPatientId(invoice.getPatient().getId());
            entity.setPatientName(invoice.getPatient().getNameComplete());
            entity.setPatientAge(invoice.getPatient().getAge());
            entity.setPatientDocument(invoice.getPatient().getDocument());
        }
        if (invoice.getDoctor() != null) {
            entity.setDoctorId(invoice.getDoctor().getId());
            entity.setDoctorName(invoice.getDoctor().getNameComplete());
        }
        if (invoice.getPolicy() != null) {
            entity.setPolicyId(invoice.getPolicy().getId());
            entity.setInsuranceCompany(
                    invoice.getPolicy().getPolicyName() != null ? invoice.getPolicy().getPolicyName().name() : null);
            entity.setPolicyNumber(invoice.getPolicy().getPolicyNumber());
            if (invoice.getPolicy().getPolicyTerminationDate() != null) {
                entity.setPolicyEndDate(new Date(invoice.getPolicy().getPolicyTerminationDate().getTime()));
            }
            // Suponiendo que la vigencia es la diferencia en días entre hoy y la fecha de
            // terminación
            if (invoice.getPolicy().getPolicyTerminationDate() != null) {
                long diff = invoice.getPolicy().getPolicyTerminationDate().getTime() - System.currentTimeMillis();
                entity.setPolicyValidityDays((int) (diff / (1000 * 60 * 60 * 24)));
            }
        }
        entity.setAmount(invoice.getAmount());
        // Asignar total igual a amount (ajustar según lógica de negocio)
        entity.setTotal(invoice.getAmount());
        entity.setCopay(invoice.getCopay());
        entity.setInsuranceCovered(invoice.getInsuranceCovered());
        // Asignar insurerShare igual a insuranceCovered (ajustar según lógica de
        // negocio)
        entity.setInsurerShare(invoice.getInsuranceCovered());
        // Asignar patientCopay igual a copay (ajustar según lógica de negocio)
        entity.setPatientCopay(invoice.getCopay());
        return entity;
    }
}
