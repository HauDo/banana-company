package com.axonactive.center;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.axonactive.center.model.employee.Employee;
import com.axonactive.center.repository.employee.EmployeeRepository;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

@Component
public class CenterApplicationController implements Initializable {

	@FXML
	private ListView<Employee> employeeListView;

	@FXML
	private Label amountEmployee;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Employee> employees = EmployeeRepository.findAll();
		employeeListView.getItems().addAll(employees);
		amountEmployee.setText(String.valueOf(employees.size()));
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						List<Employee> employees = EmployeeRepository.findAll();
						employeeListView.getItems().clear();
						employeeListView.getItems().addAll(employees);
						amountEmployee.setText(String.valueOf(employees.size()));
					}
				});
			}
		}, 0, 2000);
	}

}
