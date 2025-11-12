package app.domain.ports;

import app.domain.model.Visit;
import java.util.List;

public interface VisitRepositoryPort {
    List<Visit> findAll();
    List<Visit> findByPaciente(Long pacienteId);
    Visit save(Visit visit);
    void delete(Long id);
}
