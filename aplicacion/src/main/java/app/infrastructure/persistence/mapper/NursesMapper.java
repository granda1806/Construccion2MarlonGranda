package app.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import app.domain.model.VitalSignsRecord;
import app.domain.model.Procedure;
import app.domain.model.Observation;
import app.infrastructure.persistence.entities.*;

@Component
public class NursesMapper {

    // --- VitalSigns ---
    public VitalSignsEntity toVitalSignsEntity(VitalSignsRecord record) {
        if (record == null) return null;

        VitalSignsEntity entity = new VitalSignsEntity();
        entity.setId(record.getId());
        entity.setBloodPressure(record.getBloodPressure());
        entity.setTemperature(record.getTemperature());
        entity.setPulse(record.getPulse());
        entity.setBloodOxygenLevel(record.getBloodOxygenLevel());
        // Si tienes orden médica, podrías setearla aquí
        return entity;
    }

    // --- Procedure ---
    public ProcedureEntity toProcedureEntity(Procedure procedure) {
        if (procedure == null) return null;

        ProcedureEntity entity = new ProcedureEntity();
        // Mapear aquí los atributos reales
        return entity;
    }

    // --- Observation ---
    public ObservationEntity toObservationEntity(Observation observation) {
        if (observation == null) return null;

        ObservationEntity entity = new ObservationEntity();
        // Mapear aquí los atributos reales
        return entity;
    }
}
