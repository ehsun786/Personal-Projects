package eLearning.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Lecturer")
public class Lecturer extends User {

	@ElementCollection
	Set<Integer> courseList = new HashSet<Integer>();
	@Column
	private String networking;
	@Column
	private String description;
	@Column
	private String mail;
	@Column
	private boolean authorized = false;
	@Column
	int securityCode;
	@Column
	String bucketName;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	List<Meetup> meetupList = new ArrayList<Meetup>();
	@Column
	private String cvName;
	@Column
	private String coverLetterName;
	@Transient
	private String cvNameForAdmin;
	@Transient
	private String clNameForAdmin;
	
	
	
	public Lecturer() {
		
	}
	
	public boolean isAuthorized() {
		return authorized;
	}
	
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
	
	public Set<Integer> getCourseList() {
		return courseList;
	}
	
	public void setCourseList(Set<Integer> courseList) {
		this.courseList = courseList;
	}
	
	public String getNetworking() {
		return networking;
	}

	public void setNetworking(String networking) {
		this.networking = networking;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	public int getSecurityCode() {
		return securityCode;
	}
	
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public List<Meetup> getMeetupList() {
		return meetupList;
	}
	
	public void setMeetupList(List<Meetup> meetupList) {
		this.meetupList = meetupList;
	}
	public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	public String getCoverLetterName() {
		return coverLetterName;
	}

	public void setCoverLetterName(String coverLetterName) {
		this.coverLetterName = coverLetterName;
	}
	public String getCvNameForAdmin() {
		return cvNameForAdmin;
	}

	public void setCvNameForAdmin(String cvNameForAdmin) {
		this.cvNameForAdmin = cvNameForAdmin;
	}

	public String getClNameForAdmin() {
		return clNameForAdmin;
	}

	public void setClNameForAdmin(String clNameForAdmin) {
		this.clNameForAdmin = clNameForAdmin;
	}

}
