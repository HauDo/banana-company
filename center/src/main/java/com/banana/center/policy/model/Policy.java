package com.banana.center.policy.model;

public class Policy {
	private final String name;
	private int id;

	public Policy(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
}
