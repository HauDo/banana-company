package com.banana.center.policy.model;

import static java.util.stream.Collectors.toList;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Policies {
	private final ConcurrentLinkedQueue<Policy> policies;
	private AtomicInteger counter = new AtomicInteger(1);

	public Policies() {
		policies = new ConcurrentLinkedQueue<>();
	}

	private Policies(Stream<Policy> policyStream) {
		this.policies = new ConcurrentLinkedQueue<>(policyStream.collect(toList()));
	}

	public boolean isEmpty() {
		return policies.isEmpty();
	}

	public void add(Policy policy) {
		policy.setId(counter.get());
		policies.add(policy);
	}

	public Policies filterBy(String id) {
		return new Policies(policies.stream().filter(x -> x.getId() == Integer.valueOf(id)));
	}
}
