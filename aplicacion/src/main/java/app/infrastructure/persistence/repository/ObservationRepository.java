
package app.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.infrastructure.persistence.entities.ObservationEntity;

public interface ObservationRepository extends JpaRepository<ObservationEntity, Long> {}