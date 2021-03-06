package project;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
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
	
	private Collection<Project> connectedUPN;
	private Collection<Project> vAllProjects;
	private Collection<Employee> vAllEmployees;
	
	public UnifiedMB() {
		
	}
	
	@PostConstruct
	public void postConstruct(){
		connectedUPN = getConnectedUserProjectsName();
		vAllProjects = getAllProjects();
		vAllEmployees = getAllEmployees();
	}
	
	public Collection<Project> getConnectedUPN()
	{
		return connectedUPN;
	}
	
	public void setConnectedUPN(Collection<Project> _connectedUPN)
	{
		connectedUPN = _connectedUPN;
	}
	
	public Collection<Employee> getVAllEmployees()
	{
		return vAllEmployees;
	}
	
	public void setVAllEmployees(Collection<Employee> _vAllEmployees)
	{
		vAllEmployees = _vAllEmployees;
	}
	
	public Collection<Project> getVAllProjects()
	{
		return vAllProjects;
	}
	
	public void setVAllProjects(Collection<Project> _vAllProjects)
	{
		vAllProjects = _vAllProjects;
	}
	
	
	public Collection<Activity> getActivitiesProject(Project project)
	{
		Collection<Activity> ca = new ArrayList<Activity>();
		ca.addAll(project.getActivities());
		return ca;
	}
	
	public Collection<Project> getAllProjects()
	{
		return fps.findAllProject();
	}
	
	public Collection<Employee> getAllEmployees()
	{
		return fes.findAllEmployee();
	}
	
	public Collection<Project> getConnectedUserProjectsName()
	{
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
		Employee emp = null;
		
        // If the user is logged in
        if( userSession.get("username") != null) {
        	emp = fes.findItem((String) userSession.get("username"));
        }
        
		// On r�cup�re l'user actuel
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
		
		// On r�cup�re le bean permettant de r�cup�rer l'employ� actuellement connect�
		// TODO : Savoir comment r�cup�rer un bean
		//Employee employeeConnecte = talkingBean.getCurentEmp();
		//loginBean.getPassword();		
		Collection<Employee> projectEmployees = new ArrayList<Employee>();
		
		// On rajoute le chef de projet en premier
		if(project.getEmployee().getIdemployee() != emp.getIdemployee())
		{
			projectEmployees.add(project.getEmployee());
		}
		
		// Puis les employ�es de chaque activit�
		Set<Activity> activities = project.getActivities();
		for(Activity act : activities)
		{
			// Si il existe d�j� on ne l'ajoute pas
			// TODO: On ne s'ajoute pas soi-m�me
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
