package model;

// Generated Dec 19, 2012 8:13:44 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RealmRoles generated by hbm2java
 */
@Entity
@Table(name = "realm_roles")
public class RealmRoles implements java.io.Serializable {

	private RealmRolesId id;

	public RealmRoles() {
	}

	public RealmRoles(RealmRolesId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userName", column = @Column(name = "user_name", nullable = false, length = 45)),
			@AttributeOverride(name = "userRole", column = @Column(name = "user_role", nullable = false, length = 45)) })
	public RealmRolesId getId() {
		return this.id;
	}

	public void setId(RealmRolesId id) {
		this.id = id;
	}

}
