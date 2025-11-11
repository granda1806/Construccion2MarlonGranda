package app.infrastructure.persistence.repository;

<<<<<<< HEAD
import app.domain.model.MedicalOrder;
import java.util.List;

public interface MedicalOrderRepository
{

    void save(MedicalOrder order);

    MedicalOrder findByOrderNumber(String orderNumber);

    List<MedicalOrder> findAll();

    void deleteByOrderNumber(String orderNumber);
    
=======
import org.springframework.data.jpa.repository.JpaRepository;
import app.infrastructure.persistence.entities.MedicalOrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalOrderRepository extends JpaRepository<MedicalOrderEntity, Long> {
    public MedicalOrderEntity findByOrderNumber(String orderNumber);
    

>>>>>>> 930cfa164b6bb60b05a6ac40ae6032838b2a843b
}
