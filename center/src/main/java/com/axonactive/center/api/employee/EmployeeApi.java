package com.axonactive.center.api.employee;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axonactive.center.model.employee.Employee;
import com.axonactive.center.model.employee.Status;
import com.axonactive.center.model.employee.WorkingStatus;
import com.axonactive.center.repository.employee.EmployeeRepository;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeApi {

	@GetMapping
	public List<Employee> get() {
		return EmployeeRepository.findAll();
	}

	@PostMapping
	public Employee add(@RequestBody String account) {
		return EmployeeRepository.add(new Employee(account, Status.ACTIVE, WorkingStatus.CHECKED_OUT));
	}
	
	@GetMapping("{account}")
	public Employee get(@PathVariable String account) {
		return null;
	}
	
	@PostMapping("{account}/checkin")
	public Employee checkin(@PathVariable String account) {
		return null;
	}

	@PostMapping("{account}/checkout")
	public Employee checkout(@PathVariable String account) {
		return null;
	}

}
