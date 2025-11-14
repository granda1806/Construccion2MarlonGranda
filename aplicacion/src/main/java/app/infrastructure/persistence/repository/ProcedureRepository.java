package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.infrastructure.persistence.entities.ProcedureEntity;

public interface ProcedureRepository extends JpaRepository<ProcedureEntity, Long> {}
