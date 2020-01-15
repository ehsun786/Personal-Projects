package com.ehsun.eventsavy.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;

import com.google.gson.annotations.Expose;

@Entity
public class User {

	@CreationTimestamp
	@Column(updatable = false)
	protected Instant created = Instant.now();
	@Column
	private String email;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
	private List<Event> eventsCreated = new ArrayList<>();
	@Column
	private String fullName;
	@Column
	private String password;
	@Column
	int securityCode;

	@Column
	private boolean suspended = false;

	@Id
	@RestResource
	@Expose
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
