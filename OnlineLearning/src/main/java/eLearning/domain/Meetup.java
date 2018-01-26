package eLearning.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Meetup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int meetupId;
	@Column
	private String description;
	@Column
	private String buildingNumber;
	@Column
	private String street;
	@Column
	private String postcode;
	@Column
	private String dayAndDate;
	@Column
	private LocalDate meetupCreationDate = LocalDate.now();
	@Column
	private int atHour;
	@Column
	private int atMinute;
	@Column
	private int estimatedHours;
	@Column
	private int estimatedMinutes;
	@Column
	private boolean recurring;
	@OneToOne(fetch = FetchType.LAZY)
	private Course course;
	@ManyToMany
	@JoinTable(name = "learner_meetup", joinColumns = @JoinColumn(name = "meetupId", referencedColumnName = "meetupId"), inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
	private List<Learner> learners = new ArrayList<>();

	public int getMeetupId() {
		return meetupId;
	}

	public void setMeetupId(int id) {
		this.meetupId = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getBuildingNumber() {
		return buildingNumber;
	}
	
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String location) {
		this.postcode = location;
	}
	
	public String getDayAndDate() {
		return dayAndDate;
	}
	
	public void setDayAndDate(String dayAndDate) {
		this.dayAndDate = dayAndDate;
	}

	public LocalDate getMeetupCreationDate() {
		return meetupCreationDate;
	}

	public void setMeetupCreationDate(LocalDate meetupCreationDate) {
		this.meetupCreationDate = meetupCreationDate;
	}

	public int getAtHour() {
		return atHour;
	}

	public void setAtHour(int atHour) {
		this.atHour = atHour;
	}

	public int getAtMinute() {
		return atMinute;
	}

	public void setAtMinute(int atMinute) {
		this.atMinute = atMinute;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public int getEstimatedMinutes() {
		return estimatedMinutes;
	}

	public void setEstimatedMinutes(int estimatedMinutes) {
		this.estimatedMinutes = estimatedMinutes;
	}

	public boolean getRecurring() {
		return recurring;
	}
	
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Learner> getLearners() {
		return learners;
	}

	public void setLearners(List<Learner> learners) {
		this.learners = learners;
	}

}
