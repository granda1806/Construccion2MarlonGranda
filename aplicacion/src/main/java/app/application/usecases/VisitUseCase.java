package app.application.usecases;

import app.domain.model.Visit;
import app.domain.ports.VisitRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitUseCase {

    private final VisitRepositoryPort visitRepository;

    public VisitUseCase(VisitRepositoryPort visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> listar() {
        return visitRepository.findAll();
    }

    public List<Visit> listarPorPaciente(Long pacienteId) {
        return visitRepository.findByPaciente(pacienteId);
    }

    public Visit guardar(Visit visit) {
        // Simplemente delega al adapter
        return visitRepository.save(visit);
    }

    public void eliminar(Long id) {
        visitRepository.delete(id);
    }
}
