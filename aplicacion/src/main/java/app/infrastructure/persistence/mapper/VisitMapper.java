package app.infrastructure.persistence.mapper;

import app.domain.model.Visit;
import app.infrastructure.persistence.entities.VisitEntity;

public class VisitMapper {

    public static Visit toDomain(VisitEntity entity) {
        Visit visit = new Visit();
        visit.setId(entity.getId());
        if (entity.getPaciente() != null) {
            visit.setPacienteId(entity.getPaciente().getId());
        }
        visit.setFechaVisita(entity.getFechaVisita());
        visit.setMotivo(entity.getMotivo());
        visit.setObservaciones(entity.getObservaciones());
        return visit;
    }

    public static VisitEntity toEntity(Visit visit) {
        VisitEntity entity = new VisitEntity();
        entity.setId(visit.getId());
        entity.setFechaVisita(visit.getFechaVisita());
        entity.setMotivo(visit.getMotivo());
        entity.setObservaciones(visit.getObservaciones());
        // NO asignamos paciente aquí; se hace en el adapter
        return entity;
    }
}
