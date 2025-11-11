package app.infrastructure.persistence.repository;

import app.domain.model.MedicalOrder;
import java.util.List;

public interface MedicalOrderRepository
{

    void save(MedicalOrder order);

    MedicalOrder findByOrderNumber(String orderNumber);

    List<MedicalOrder> findAll();

    void deleteByOrderNumber(String orderNumber);
    
}
