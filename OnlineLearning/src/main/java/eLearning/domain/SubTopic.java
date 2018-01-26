package eLearning.domain;

import java.io.File;

import javax.mail.Multipart;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class SubTopic {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int subtopicId;
	@Column
	private String description;
	@Column
	String file1;
	@Transient
	Multipart file1Temp; //These will just be used for jsp/controller purposes and the files will persist in the cloud(AWS) and
	@Column
	private String name;
	@Column
	private String fileExtension;
	@Column
	private boolean accessed;
	

	public int getId() {
		return subtopicId;
	}

	public void setId(int id) {
		this.subtopicId = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public Multipart getFile1Temp() {
		return file1Temp;
	}

	public void setFile1Temp(Multipart file1Temp) {
		this.file1Temp = file1Temp;
	}
	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public boolean isAccessed() {
		return accessed;
	}

	public void setAccessed(boolean accessed) {
		this.accessed = accessed;
	}
}
