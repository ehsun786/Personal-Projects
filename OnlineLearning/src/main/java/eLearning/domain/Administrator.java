package eLearning.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends User {
	@Column
	int securityCode;
	
	public Administrator() {
		
	}
	
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	
	public int getSecurityCode() {
		return securityCode;
	}

}
