package app.infrastructure.persistence.mapper;

import app.domain.model.ClinicalHistoryRecord;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.entities.DoctorEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class DoctorMapper {

    // ==================== ENTITY → DOMAIN ====================
    public static ClinicalHistoryRecord toDomain(MedicalHistoryEntity entity) {
        if (entity == null) {
            return null;
        }

        ClinicalHistoryRecord record = new ClinicalHistoryRecord();

        // ID
        if (entity.getId() != null) {
            record.setId(entity.getId());
        }

        // Conversión segura de fecha (Date o String)
        LocalDate parsedDate;
        try {
            Object rawDate = entity.getDate();
            if (rawDate instanceof Date dateObj) {
                parsedDate = dateObj.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            } else if (rawDate instanceof String strDate && !strDate.isBlank()) {
                parsedDate = LocalDate.parse(strDate);
            } else {
                parsedDate = LocalDate.now();
            }
        } catch (Exception e) {
            parsedDate = LocalDate.now();
        }
        record.setDate(parsedDate);

        // Doctor
        if (entity.getDoctor() != null && entity.getDoctor().getId() != null) {
            record.setDoctorId(entity.getDoctor().getId());
        }

        // Documentos y campos simples
        if (entity.getPatientDocument() != null)
        {
            try
            {
                
                Long patientDoc = entity.getPatientDocument();
                if (patientDoc > 0) {
                    record.setPatientDocument(patientDoc);
                } else {
                    System.out.println("Documento del paciente inválido: " + entity.getPatientDocument());
                }
            } catch (NumberFormatException e) {
                System.out.println("Documento del paciente no es un número válido: " + entity.getPatientDocument());
            }
        } else {
            System.out.println("Documento del paciente inválido o nulo");
        }

        record.setReasonForConsultation(entity.getReasonForConsultation());
        record.setSymptoms(entity.getSymptoms());
        record.setDiagnosis(entity.getDiagnosis());

        // Inicialización segura de listas
        record.setPrescriptions(new ArrayList<>());
        record.setProcedures(new ArrayList<>());

        return record;
    }

    // ==================== DOMAIN → ENTITY ====================
    public static MedicalHistoryEntity toEntity(ClinicalHistoryRecord record) {
        if (record == null) {
            return null;
        }

        MedicalHistoryEntity entity = new MedicalHistoryEntity();

        // ID
        if (record.getId() != null) {
            entity.setId(record.getId());
        }

        // LocalDate → java.util.Date
        try {
            if (record.getDate() != null) {
                Date date = Date.from(record.getDate()
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
                entity.setDate(date);
            } else {
                entity.setDate(new Date());
            }
        } catch (Exception e) {
            entity.setDate(new Date());
        }

        // Documento (Long → String)
        if (record.getPatientDocument() != null && record.getPatientDocument() > 0) {
            entity.setPatientDocument(Long.valueOf(record.getPatientDocument()));
        } else {
            System.out.println("Documento del paciente inválido o nulo: " + record.getPatientDocument());
        }

        // Campos simples
        entity.setReasonForConsultation(record.getReasonForConsultation());
        entity.setSymptoms(record.getSymptoms());
        entity.setDiagnosis(record.getDiagnosis());

        // Observaciones automáticas si no existen
        if (entity.getObservations() == null || entity.getObservations().isBlank()) {
            entity.setObservations("Generado automáticamente por DoctorMapper");
        }

        // Doctor
        if (record.getDoctorId() != null) {
            DoctorEntity doctor = new DoctorEntity();
            doctor.setId(record.getDoctorId());
            entity.setDoctor(doctor);
        }

        return entity;
    }
}
