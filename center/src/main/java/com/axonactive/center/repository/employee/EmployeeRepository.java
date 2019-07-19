package com.axonactive.center.repository.employee;

import java.util.ArrayList;
import java.util.List;

import com.axonactive.center.model.employee.Employee;
import com.axonactive.center.model.employee.WorkingStatus;

public class EmployeeRepository {
	private static List<Employee> employees = new ArrayList<>();
	
	static {
		employees.add(new Employee("May Thu Khin", WorkingStatus.INACTIVE));
		employees.add(new Employee("Phong Pham", WorkingStatus.INACTIVE));
	}
	
	private EmployeeRepository() {}

	public static List<Employee> findAll() {
		return employees;
	}

	public static Employee save(String name) {
		Employee employee = new Employee();
		employee.setName(name);
		employee.setWorkingStatus(WorkingStatus.INACTIVE);
		employees.add(employee);
		return employee;
	}

}
