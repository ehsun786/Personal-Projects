package eLearning.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Learner")
public class Learner extends User {

	@ElementCollection
	private Set<Integer> accessedSubTopics = new HashSet<Integer>();
	@ElementCollection
	private List<String> badges = new ArrayList<>();
	@ElementCollection
	private Map<Integer,String> comments = new HashMap<>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Meetup> meetups = new ArrayList<>();
	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "learner")
	//private List<Course> courseList = new ArrayList<>();
	@ElementCollection
	private Map<Integer, Integer> courseRatings = new HashMap<Integer, Integer>();
	//@OneToMany()
	@ElementCollection
	private Map<Integer, Reviews> courseReviews = new HashMap<Integer, Reviews>();

	
    @ManyToMany
    @JoinTable(name="L_C")
	private List<Course> courseList = new ArrayList<>();

	
	@Column
	private String profilePicName;

	public Learner() {
		
	}

	public Set<Integer> getAccessedSubTopics() {
		return accessedSubTopics;
	}

	public List<String> getBadges() {
		return badges;
	}

	public Map<Integer,String> getComments() {
		return comments;
	}
	
	public List<Meetup> getMeetups() {
		return meetups;
	}
	
	public List<Course> getCourseList() {
		return courseList;
	}

	public void setAccessedSubTopics(Set<Integer> accessedSubTopics) {
		this.accessedSubTopics = accessedSubTopics;
	}

	public void setBadges(List<String> badges) {
		this.badges = badges;
	}

	public void setComments(Map<Integer,String> comments) {
		this.comments = comments;
	}
	
	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	public void setCourseRatings(Map<Integer, Integer> courseRatings) {
		this.courseRatings = courseRatings;
	}
	
	public Map<Integer, Integer> getCourseRatings() {
		return courseRatings;
	}
	public String getProfilePicName() {
		return profilePicName;
	}

	public void setProfilePicName(String profilePicName) {
		this.profilePicName = profilePicName;
	}
	public Map<Integer, Reviews> getCourseReviews() {
		return courseReviews;
	}
	public void setCourseReviews(Map<Integer, List<String>> courseReviews) {
		
	}

}
