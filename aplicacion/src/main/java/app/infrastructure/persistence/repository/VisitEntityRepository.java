
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitEntityRepository extends JpaRepository<VisitEntity, Long> {
    List<VisitEntity> findByPaciente_Id(Long pacienteId);
}
