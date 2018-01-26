package eLearning.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mail {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int mailId;
	@Column
	private String to;
	@Column
	private String from;
	@Column
	private LocalDate dateUploaded = LocalDate.now();
	@Column
	private String text;

	public int getId() {
		return mailId;
	}

	public void setId(int id) {
		this.mailId = id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}

	public LocalDate getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(LocalDate dateUploaded) {
		this.dateUploaded = dateUploaded;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
