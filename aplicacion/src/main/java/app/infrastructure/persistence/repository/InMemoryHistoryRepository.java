package app.infrastructure.persistence.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryHistoryRepository
{

    private final Map<String, Map<String, Map<String, String>>> historyData = new HashMap<>();

    public boolean hasHistory(String patientId)
    {
        
        return historyData.containsKey(patientId);
        
    }

    public void saveRecord(String patientId, String date, Map<String, String> record)
    {
        
        historyData.computeIfAbsent(patientId, k -> new HashMap<>()).put(date, record);
        
    }
    
    public void showAll()
    {
        
        System.out.println(historyData);
        
    }

    public Map<String, String> findRecord(String patientId, String date)
    {
        
        return historyData.getOrDefault(patientId, new HashMap<>()).get(date);
        
    }

    public Map<String, Map<String, String>> findAllByPatient(String patientId)
    {
        
        return historyData.getOrDefault(patientId, new HashMap<>());
        
    }
    
}
