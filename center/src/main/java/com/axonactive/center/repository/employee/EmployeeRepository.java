package com.axonactive.center.repository.employee;

import java.util.ArrayList;
import java.util.List;

import com.axonactive.center.model.employee.Employee;
import com.axonactive.center.model.employee.Status;
import com.axonactive.center.model.employee.WorkingStatus;

public class EmployeeRepository {

	private static List<Employee> employees = new ArrayList<>();

	static {
		employees.add(new Employee("phongpnq", Status.ACTIVE, WorkingStatus.CHECKED_OUT));
	}

	private EmployeeRepository() {}

	public static List<Employee> findAll() {
		return employees;
	}

	public static Employee add(Employee employee) {
		employees.add(employee);
		return employee;
	}

}
