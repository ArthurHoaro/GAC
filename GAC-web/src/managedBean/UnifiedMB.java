package managedBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

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
	
	public Collection<Employee> getEmployeessProject(Project project)
	{
		Collection<Employee> projectEmployees = new ArrayList<Employee>();
		
		// On rajoute le chef de projet en premier
		projectEmployees.add(project.getEmployee());
		
		// Puis les employées de chaque activité
		Set<Activity> activities = project.getActivities();
		for(Activity act : activities)
		{
			// Si il existe déjà on ne l'ajoute pas
			if(!projectEmployees.contains(act.getEmployee()))
			{
				projectEmployees.add(act.getEmployee());
			}
		}
		return projectEmployees;
	}
	
	public String getName(){
		return fps.findItem(1).getName();
	}
}
