package app.application.usecases;

import app.domain.model.Employee;

public class CreateEmployeeUseCase {

    public Employee execute(int id, String name) {
        Employee employee = new Employee(id, name);
        System.out.println("Empleado creado: " + employee);
        return employee;
    }
}
