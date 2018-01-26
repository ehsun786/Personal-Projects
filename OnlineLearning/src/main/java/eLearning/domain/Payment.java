package eLearning.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int paymentId;
	@Column
    private LocalDate date = LocalDate.now();
	@Column
	private Double amount;
	@OneToOne(fetch=FetchType.LAZY)
	private Learner payee;
	@OneToOne(fetch=FetchType.LAZY)
	private Course course;
	
	public int getPayment_id() {
		return paymentId;
	}
	public void setPayment_id(int payment_id) {
		this.paymentId = payment_id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Learner getPayee() {
		return payee;
	}
	public void setPayee(Learner payee) {
		this.payee = payee;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

}
