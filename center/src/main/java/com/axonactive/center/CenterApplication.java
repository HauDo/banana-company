package com.axonactive.center;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.axonactive.center.model.employee.Employee;
import com.axonactive.center.repository.employee.EmployeeRepository;

@SpringBootApplication
public class CenterApplication {

	public static void main(final String[] args) {
		SpringApplication.run(CenterApplication.class, args);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				List<Employee> employees = EmployeeRepository.findAll();
				System.out.println(employees.toString());
			}
		}, 0, 4000);
	}

}
