package eLearning.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GoogleAuthentication {
	@Id
	String username;
	@Column
	int codeSent;
	
	public int getCodeSent() {
		return codeSent;
	}
	public String getUsername() {
		return username;
	}
	public void setCodeSent(int codeSent) {
		this.codeSent = codeSent;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
