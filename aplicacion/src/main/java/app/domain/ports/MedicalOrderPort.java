package app.domain.ports;

import app.domain.model.MedicalOrder;
import java.util.List;

public interface MedicalOrderPort {
    void save(MedicalOrder order);
    MedicalOrder findByOrderNumber(String orderNumber);
    List<MedicalOrder> findAll();
    void deleteByOrderNumber(String orderNumber);
}
