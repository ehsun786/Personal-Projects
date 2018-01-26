package eLearning.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Multipart;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int courseId;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private LocalDate dateUploaded = LocalDate.now();
	@Column
	private Double price;
	@Column
	private double rating=0; //Max 5
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, orphanRemoval = true)
	private List<Chapter> chapterList = new ArrayList<>();
	@OneToOne
	private Lecturer lecturer;
	//@ManyToMany
	//@JoinTable(name = "learner_course", joinColumns = @JoinColumn(name = "courseId", referencedColumnName = "courseId"), inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
	//private List<Learner> learnerList = new ArrayList<>();
	@Column
	private String courseImageName;
	@Transient
	private Multipart file; //These will just be used for jsp/controller purposes and the files will persist in the cloud(AWS)
	@Column
	private String tempImageLinkHolder;
    @ManyToMany(mappedBy="courseList")
	private List<Learner> learnerList = new ArrayList<>();
	@Transient
	private Map<Learner,String> myReview = new HashMap<>();

	

	public Course() {
		
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateUploaded() {
		return dateUploaded;
	}
	public void setDateUploaded(LocalDate dateUploaded) {
		this.dateUploaded = dateUploaded;
	}
	public List<Chapter> getChapterList() {
		return chapterList;
	}
	public void setChapterList(List<Chapter> courseList) {
		this.chapterList = courseList;
	}
	public Lecturer getLecturer() {
		return lecturer;
	}
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	
	public List<Learner> getLearnerList() {
		return learnerList;
	}
	
	public void setLearnerList(List<Learner> learnerList) {
		this.learnerList = learnerList;
	}
	public String getCourseImageName() {
		return courseImageName;
	}

	public void setCourseImageName(String courseImageName) {
		this.courseImageName = courseImageName;
	}

	public Multipart getCourseImgFile() {
		return file;
	}

	public void setCourseImgFile(Multipart courseImgFile) {
		this.file = courseImgFile;
	}
	public String getTempImageLinkHolder() {
		return tempImageLinkHolder;
	}

	public void setTempImageLinkHolder(String tempImageLinkHolder) {
		this.tempImageLinkHolder = tempImageLinkHolder;
	}
	public Map<Learner, String> getMyReview() {
		return myReview;
	}

	public void setMyReview(Map<Learner, String> myReview) {
		this.myReview = myReview;
	}


}
