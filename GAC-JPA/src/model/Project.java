
package model;

// Generated 14 janv. 2013 16:36:44 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "project")
public class Project implements java.io.Serializable {

	private Integer idproject;
	private Employee employee;
	private String name;
	private Integer budject;
	private String description;
	private Set<Activity> activities = new HashSet<Activity>(0);

	public Project() {
	}

	public Project(Employee employee) {
		this.employee = employee;
	}

	public Project(Employee employee, String name, Integer budject,
			String description, Set<Activity> activities) {
		this.employee = employee;
		this.name = name;
		this.budject = budject;
		this.description = description;
		this.activities = activities;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idproject", unique = true, nullable = false)
	public Integer getIdproject() {
		return this.idproject;
	}

	public void setIdproject(Integer idproject) {
		this.idproject = idproject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_idemployee", nullable = false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "budject")
	public Integer getBudject() {
		return this.budject;
	}

	public void setBudject(Integer budject) {
		this.budject = budject;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

}
