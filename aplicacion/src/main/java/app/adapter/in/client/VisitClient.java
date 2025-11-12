package app.adapter.in.client;

import app.application.usecases.VisitUseCase;
import app.domain.model.Visit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit")
@CrossOrigin("*")
public class VisitClient {

    private final VisitUseCase visitUseCase;

    public VisitClient(VisitUseCase visitUseCase) {
        this.visitUseCase = visitUseCase;
    }

    @GetMapping
    public List<Visit> listar() {
        return visitUseCase.listar();
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<Visit> listarPorPaciente(@PathVariable Long pacienteId) {
        return visitUseCase.listarPorPaciente(pacienteId);
    }

    @PostMapping
    public Visit crear(@RequestBody Visit visit) {
        return visitUseCase.guardar(visit);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        visitUseCase.eliminar(id);
    }
}
