package project;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import account.Login;
import account.Profile;
import account.UserProfile;

import chat.Talking;

import model.Activity;
import model.Employee;
import model.Project;

import remote.FActivityServicesRemote;
import remote.FEmployeeServicesRemote;
import remote.FProjectServicesRemote;

@ManagedBean
@ViewScoped
public class UnifiedMB implements Serializable {	
	@EJB
	private FProjectServicesRemote fps;
	
	@EJB
	private FEmployeeServicesRemote fes;
	
	@EJB
	private FActivityServicesRemote fas;
	
	
	public UnifiedMB() {
		
	}
	
	
	public Collection<Activity> getActivitiesProject(Project project)
	{
		return fas.getAllActivitiesFromProject(project);
	}
	
	public Collection<Project> getProjectsName()
	{
		return fps.findAllProject();
	}
	
	public Collection<Project> getConnectedUserProjectsName()
	{
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
		Employee emp = null;
		
        // If the user is logged in
        if( userSession.get("username") != null) {
        	emp = fes.findItem((String) userSession.get("username"));
        }
        
		// On récupère l'user actuel
		if(emp != null)
		{
			return fps.findAllProject(emp.getEmail());
		}
		return null;
	}
	
	public Employee getCurrentEmployee()
	{
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
		Employee emp = null;
		
        // If the user is logged in
        if( userSession.get("username") != null) {
        	emp = fes.findItem((String) userSession.get("username"));
        }
        
        return emp;
	}
	
	public Collection<Employee> getEmployeessProject(Project project)
	{
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
		Employee emp = null;
		
        // If the user is logged in
        if( userSession.get("username") != null) {
        	emp = fes.findItem((String) userSession.get("username"));
        }
		
		// On récupère le bean permettant de récupérer l'employé actuellement connecté
		// TODO : Savoir comment récupérer un bean
		//Employee employeeConnecte = talkingBean.getCurentEmp();
		//loginBean.getPassword();		
		Collection<Employee> projectEmployees = new ArrayList<Employee>();
		
		// On rajoute le chef de projet en premier
		if(project.getEmployee().getIdemployee() != emp.getIdemployee())
		{
			projectEmployees.add(project.getEmployee());
		}
		
		// Puis les employées de chaque activité
		Set<Activity> activities = project.getActivities();
		for(Activity act : activities)
		{
			// Si il existe déjà on ne l'ajoute pas
			// TODO: On ne s'ajoute pas soi-même
			if(!projectEmployees.contains(act.getEmployee()) && emp != null && act.getEmployee().getIdemployee() != emp.getIdemployee())
			{
				projectEmployees.add(act.getEmployee());
			}
		}
		return projectEmployees;
	}
	
	public boolean isProjectManager(Project project, Employee employee)
	{
		if(employee != null)
		{
			if(project.getEmployee().getIdemployee().equals(employee.getIdemployee()))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getName(){
		return fps.findItem(1).getName();
	}
}
