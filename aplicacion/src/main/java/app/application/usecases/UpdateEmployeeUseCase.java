package app.application.usecases;

import app.domain.model.Employee;

public class UpdateEmployeeUseCase
{

    public void execute(Employee employee, String newName)
    {
        
        employee.setName(newName);
        System.out.println("Empleado actualizado: " + employee);
        
    }
    
}
