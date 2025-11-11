package app.infrastructure.persistence.repository;

import app.domain.model.MedicalOrder;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMedicalOrderRepository implements MedicalOrderRepository
{

    private final Map<String, MedicalOrder> orders = new HashMap<>();

    @Override
    public void save(MedicalOrder order)
    {
        
        orders.put(order.getOrderNumber(), order);
        
    }

    @Override
    public MedicalOrder findByOrderNumber(String orderNumber)
    {
        
        return orders.get(orderNumber);
        
    }

    @Override
    public List<MedicalOrder> findAll()
    {
        
        return new ArrayList<>(orders.values());
        
    }

    @Override
    public void deleteByOrderNumber(String orderNumber)
    {
        
        orders.remove(orderNumber);
        
    }
    
}
