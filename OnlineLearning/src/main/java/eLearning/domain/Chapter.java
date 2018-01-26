package eLearning.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Chapter {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public int chapterId;
	@Column
	private String name;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, orphanRemoval = true)
	private List<SubTopic> subtopics = new ArrayList<>();

	public int getId() {
		return chapterId;
	}

	public void setId(int id) {
		this.chapterId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubTopic> getSubtopics() {
		return subtopics;
	}

	public void setSubtopics(List<SubTopic> subtopics) {
		this.subtopics = subtopics;
	}

}
