package eLearning.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	private int roleId;

	private String role;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_roles", joinColumns = {
//			@JoinColumn(name = "role_id", referencedColumnName = "roleId") }, inverseJoinColumns = {
//					@JoinColumn(name = "username", referencedColumnName = "username") })
//	private Set<User> userRoles;

	public Role() {
		
	}
	
	public Integer getId() {
		return roleId;
	}

	public void setId(Integer id) {
		this.roleId = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public Set<User> getUserRoles() {
//		return userRoles;
//	}
//
//	public void setUserRoles(Set<User> userRoles) {
//		this.userRoles = userRoles;
//	}

}
