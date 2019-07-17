package com.axonactive.center.repository.employee;

import java.util.ArrayList;
import java.util.List;

import com.axonactive.center.model.employee.Employee;

public class EmployeeRepository {
	private static List<Employee> employees = new ArrayList<>();
	
	static {
		employees.add(new Employee("May Thu Khin"));
		employees.add(new Employee("Phong Pham"));
	}
	
	private EmployeeRepository() {}

	public static List<Employee> findAll() {
		return employees;
	}

	public static Employee save(String name) {
		Employee employee = new Employee(name);
		employees.add(employee);
		return employee;
	}

}
