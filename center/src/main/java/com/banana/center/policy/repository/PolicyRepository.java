package com.banana.center.policy.repository;

import com.banana.center.policy.model.Policies;
import com.banana.center.policy.model.Policy;

public class PolicyRepository {
	private final Policies policies;

    public PolicyRepository() {
    	policies = new Policies();
    	policies.add(new Policy("vn"));
    	policies.add(new Policy("mm"));
    }

    public Policies findById(String id) {
        return policies.filterBy(id);
    }
}
