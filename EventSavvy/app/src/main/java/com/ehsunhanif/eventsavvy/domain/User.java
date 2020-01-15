package com.ehsunhanif.eventsavvy.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User {


	protected Instant created = Instant.now();

	private String email;

	private List<Event> eventsCreated = new ArrayList<>();

	private String fullName;

	private String password;

	int securityCode;

	private boolean suspended = false;

	protected String username;

	public Instant getCreated() {
		return created;
	}

	public String getEmail() {
		return email;
	}

	public List<Event> getEventsCreated() {
		return eventsCreated;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPassword() {
		return password;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public String getUsername() {
		return username;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEventsCreated(List<Event> eventsCreated) {
		this.eventsCreated = eventsCreated;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
