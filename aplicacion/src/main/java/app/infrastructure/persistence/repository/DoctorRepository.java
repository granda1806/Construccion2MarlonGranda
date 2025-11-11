
package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    /**
     * Busca un médico por su número de documento (cédula)
     */
    Optional<DoctorEntity> findByDocument(Long document);

    /**
     * Verifica si existe un médico con el documento dado
     */
    boolean existsByDocument(Long document);
}
