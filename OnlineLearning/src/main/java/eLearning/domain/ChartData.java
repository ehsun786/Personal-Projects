package eLearning.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChartData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private LocalDate date = LocalDate.now();
	@Column
	int lecturers;
	@Column
	int learners;
	@Column
	int administrators;
	
	public ChartData() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getLecturers() {
		return lecturers;
	}
	
	public void setLecturers(int lecturers) {
		this.lecturers = lecturers;
	}
	
	public int getLearners() {
		return learners;
	}
	
	public void setLearners(int learners) {
		this.learners = learners;
	}
	
	public int getAdministrators() {
		return administrators;
	}
	
	public void setAdministrators(int administrators) {
		this.administrators = administrators;
	}
	
}
