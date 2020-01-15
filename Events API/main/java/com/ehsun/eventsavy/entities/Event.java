package com.ehsun.eventsavy.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Event {

	@Column
	private String endTime;
	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String title;
	@Column
	private String date;
	@Column
	private String category;
	@Column
	private String startTime;
	@ElementCollection
	private Set<String> attendees = new HashSet<String>();
	@Column
	private String username;

	@Column
	private String longitude;

	@Column
	private String latitude;

	public Set<String> getAttendees() {
		return attendees;
	}

	public String getCategory() {
		return category;
	}

	public String getDate() {
		return date;
	}

	public String getEndTime() {
		return endTime;
	}

	public Long getId() {
		return id;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	public String getStartTime() {
		return startTime;
	}

	public String getTitle() {
		return title;
	}

	public String getUsername() {
		return username;
	}

	public void setAttendees(Set<String> attendees) {
		this.attendees = attendees;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
