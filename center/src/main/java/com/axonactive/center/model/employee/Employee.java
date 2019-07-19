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
	private String name;
	private WorkingStatus workingStatus;
	
	@Override
	public String toString() {
		return name + " | " + workingStatus;
	}

}