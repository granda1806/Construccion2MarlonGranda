package app.application.usecases;

import app.domain.model.Employee;
import java.util.HashMap;
import java.util.Map;

public class EmployeeUseCase {
    private final Map<Integer, Employee> employees = new HashMap<>();

    
    public Employee create(Employee employee) {
        employees.put(employee.getId(), employee);
        return employee;
    }

    
    public Employee update(int id, Employee employee) {
        Employee existing = employees.get(id);
        if (existing == null) {
            throw new RuntimeException("Empleado no encontrado con id: " + id);
        }
        existing.updateFrom(employee);
        return existing;
    }

    
    public Employee findById(int id) {
        return employees.get(id);
    }
}
