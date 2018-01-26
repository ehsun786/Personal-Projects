package eLearning.domain;

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
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String recipient;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ChatMessage> sentMessages;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ChatMessage> receivedMessages;

	public Chat() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setRecipient(String receiver) {
		this.recipient = receiver;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setSentMessages(List<ChatMessage> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<ChatMessage> getSentMessages() {
		return sentMessages;
	}

	public void setReceivedMessages(List<ChatMessage> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public List<ChatMessage> getReceivedMessages() {
		return receivedMessages;
	}

}
