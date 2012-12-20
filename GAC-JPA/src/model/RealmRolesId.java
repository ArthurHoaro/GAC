package model;

// Generated 20 déc. 2012 10:08:00 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RealmRolesId generated by hbm2java
 */
@Embeddable
public class RealmRolesId implements java.io.Serializable {

	private String userName;
	private String userRole;

	public RealmRolesId() {
	}

	public RealmRolesId(String userName, String userRole) {
		this.userName = userName;
		this.userRole = userRole;
	}

	@Column(name = "user_name", nullable = false, length = 45)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_role", nullable = false, length = 45)
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RealmRolesId))
			return false;
		RealmRolesId castOther = (RealmRolesId) other;

		return ((this.getUserName() == castOther.getUserName()) || (this
				.getUserName() != null && castOther.getUserName() != null && this
				.getUserName().equals(castOther.getUserName())))
				&& ((this.getUserRole() == castOther.getUserRole()) || (this
						.getUserRole() != null
						&& castOther.getUserRole() != null && this
						.getUserRole().equals(castOther.getUserRole())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37 * result
				+ (getUserRole() == null ? 0 : this.getUserRole().hashCode());
		return result;
	}

}
