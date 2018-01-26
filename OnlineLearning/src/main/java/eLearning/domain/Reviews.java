package eLearning.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ElementCollection
	private List<String> reviews = new ArrayList<String>();
	@Column
	private boolean reviewDone=false;
	

	public int getId(){
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public List<String> getReviews() {
		return reviews;
	}
	
	public void setReveiws(List<String> reviews) {
		this.reviews = reviews;
	}
	public boolean isReviewDone() {
		return reviewDone;
	}

	public void setReviewDone(boolean reviewDone) {
		this.reviewDone = reviewDone;
	}

	
}
