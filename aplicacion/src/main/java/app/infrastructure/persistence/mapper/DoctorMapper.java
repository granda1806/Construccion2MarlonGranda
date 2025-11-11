package app.infrastructure.persistence.mapper;

import app.domain.model.ClinicalHistoryRecord;
import app.infrastructure.persistence.entities.MedicalHistoryEntity;
import app.infrastructure.persistence.entities.DoctorEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class DoctorMapper {

    public static ClinicalHistoryRecord toDomain(MedicalHistoryEntity entity) {
        if (entity == null) return null;

        ClinicalHistoryRecord record = new ClinicalHistoryRecord();

        // ID
        record.setId(entity.getId());

        // Conversión de fecha (admite String o Date)
        LocalDate parsedDate;
        try {
            if (entity.getDate() instanceof Date dateObj) {
                parsedDate = dateObj.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } else {
                parsedDate = LocalDate.parse(String.valueOf(entity.getDate()));
            }
        } catch (Exception e) {
            parsedDate = LocalDate.now();
        }
        record.setDate(parsedDate);

        // Doctor
        if (entity.getDoctor() != null) {
            record.setDoctorId(entity.getDoctor().getId());
        }

        // Campos simples (si existen)
        record.setPatientDocument(entity.getPatientDocument());
        try {
            record.setReasonForConsultation(entity.getReasonForConsultation());
            record.setSymptoms(entity.getSymptoms());
            record.setDiagnosis(entity.getDiagnosis());
        } catch (Exception ignored) {
        }

        // Inicialización de listas
        record.setPrescriptions(new ArrayList<>());
        record.setProcedures(new ArrayList<>());

        return record;
    }

    public static MedicalHistoryEntity toEntity(ClinicalHistoryRecord record) {
        if (record == null) return null;

        MedicalHistoryEntity entity = new MedicalHistoryEntity();

        if (record.getId() != null) {
            entity.setId(record.getId());
        }

        // LocalDate → Date
        try {
            LocalDate localDate = record.getDate();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            entity.setDate(date);
        } catch (Exception e) {
            entity.setDate(new Date());
        }

        // Campos simples
        entity.setPatientDocument(record.getPatientDocument());
        try {
            entity.setReasonForConsultation(record.getReasonForConsultation());
            entity.setSymptoms(record.getSymptoms());
            entity.setDiagnosis(record.getDiagnosis());
        } catch (Exception ignored) {
        }

        if (entity.getObservations() == null) {
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
