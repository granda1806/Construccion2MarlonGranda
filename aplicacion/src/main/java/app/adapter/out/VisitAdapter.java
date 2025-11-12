package app.adapter.out;

import app.domain.model.Visit;
import app.domain.ports.VisitRepositoryPort;
import app.infrastructure.persistence.entities.VisitEntity;
import app.infrastructure.persistence.mapper.VisitMapper;
import app.infrastructure.persistence.repository.PatientRepository;
import app.infrastructure.persistence.repository.VisitEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitAdapter implements VisitRepositoryPort {

    private final VisitEntityRepository visitEntityRepository;
    private final PatientRepository patientRepository;

    public VisitAdapter(VisitEntityRepository visitEntityRepository, PatientRepository patientRepository) {
        this.visitEntityRepository = visitEntityRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Visit> findAll() {
        return visitEntityRepository.findAll()
                .stream()
                .map(VisitMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByPaciente(Long pacienteId) {
        return visitEntityRepository.findByPaciente_Id(pacienteId)
                .stream()
                .map(VisitMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Visit save(Visit visit) {
        // Convertir Visit a VisitEntity
        VisitEntity entity = VisitMapper.toEntity(visit);

        // Asignar paciente existente a la entidad
        entity.setPaciente(
                patientRepository.findById(visit.getPacienteId())
                        .orElseThrow(() -> new RuntimeException("Paciente no encontrado"))
        );

        // Guardar en la BD
        VisitEntity savedEntity = visitEntityRepository.save(entity);

        // Convertir de vuelta a Visit y devolver
        return VisitMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(Long id) {
        visitEntityRepository.deleteById(id);
    }
}
