package com.ehsunhanif.eventsavvy.domain;

import java.util.HashSet;
import java.util.Set;

public class Event {


	private String endTime;

	private Long id;

	private String title;

	private String date;

	private String category;

	private String startTime;

	private Set<String> attendees = new HashSet<String>();

	private String username;

	private String longitude;

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
