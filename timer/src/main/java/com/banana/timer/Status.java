package com.banana.timer;

import java.util.HashMap;
import java.util.Map;

public enum Status {
	CHECKIN(1, "Check in"),
	PROCESS(2, "Processing..."),
	CHECKOUT(3, "Check out")
	;
	
	private int id;
	private String name;

	private static final Map<Integer, Status> lookup = new HashMap<>();
	
	static {
	    for (Status status: Status.values()) {
	        lookup.put(status.getId(), status);
	    }
	}
	
	private Status(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Status getById(int id) {
		return lookup.get(id);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
