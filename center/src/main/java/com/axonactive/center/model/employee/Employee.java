package com.axonactive.center.model.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private String account;
	private Status status;
	private WorkingStatus workingStatus;
	
	@Override
	public String toString() {
		return account + " | " + status + " | " + workingStatus;
	}

}