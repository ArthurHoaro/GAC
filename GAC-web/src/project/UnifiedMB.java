package project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import account.Login;
import account.UserProfile;

import chat.Talking;

import model.Activity;
import model.Employee;
import model.Project;

import remote.FEmployeeServicesRemote;
import remote.FProjectServicesRemote;

@ManagedBean
@SessionScoped
public class UnifiedMB {	
	@EJB
	private FProjectServicesRemote fps;
	
	@EJB
	private FEmployeeServicesRemote fes;
	
	public UnifiedMB() {
		// TODO Auto-generated constructor stub
	}
	
	public Collection<Project> getProjectsName()
	{
		return fps.findAllProject();
	}
	
	public Collection<Project> getConnectedUserProjectsName()
	{
		// On r�cup�re l'user actuel
		Talking currentTalking = (Talking) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("talking");
		if(currentTalking != null && currentTalking.getCurentEmp() != null)
		{
			return fps.findAllProject(currentTalking.getCurentEmp().getEmail());
		}
		return null;
	}
	
	public Collection<Employee> getEmployeessProject(Project project)
	{
		// On r�cup�re le bean permettant de r�cup�rer l'employ� actuellement connect�
		// TODO : Savoir comment r�cup�rer un bean
		//Employee employeeConnecte = talkingBean.getCurentEmp();
		//loginBean.getPassword();
		Talking currentTalking = (Talking) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("talking");
		
		Collection<Employee> projectEmployees = new ArrayList<Employee>();
		
		// On rajoute le chef de projet en premier
		projectEmployees.add(project.getEmployee());
		
		// Puis les employ�es de chaque activit�
		Set<Activity> activities = project.getActivities();
		for(Activity act : activities)
		{
			// Si il existe d�j� on ne l'ajoute pas
			// TODO: On ne s'ajoute pas soi-m�me
			if(!projectEmployees.contains(act.getEmployee()) && act.getEmployee().getIdemployee() != currentTalking.getCurentEmp().getIdemployee())
			{
				projectEmployees.add(act.getEmployee());
			}
		}
		return projectEmployees;
	}
	
	public boolean isProjectManager(Project project, Employee employee)
	{
		if(project.getEmployee().getIdemployee().equals(employee.getIdemployee()))
		{
			return true;
		}
		return false;
	}
	
	public String getName(){
		return fps.findItem(1).getName();
	}
}
