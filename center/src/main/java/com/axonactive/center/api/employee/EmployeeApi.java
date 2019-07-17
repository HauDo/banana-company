package com.axonactive.center.api.employee;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axonactive.center.model.employee.Employee;
import com.axonactive.center.repository.employee.EmployeeRepository;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeApi {
	
	@GetMapping
	public List<Employee> get() {
		return EmployeeRepository.findAll();
	}

	@PostMapping
	public Employee create(@RequestBody String name) {
		return EmployeeRepository.save(name);
	}

}
