package remote;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.AvancementServiceLocal;
import local.EmployeeService;
import local.EmployeeServiceLocal;
import local.ProjectServiceLocal;
import model.Activity;
import model.Employee;
import model.Project;

/**
 * Session Bean implementation class FProjectServices
 */
@Stateless
public class FProjectServices implements FProjectServicesRemote {

	/**
     * Default constructor. 
     */
	@EJB
    private ProjectServiceLocal projectService;
	@EJB
	private EmployeeServiceLocal employeeService;
	@EJB
	private AvancementServiceLocal avancementService;
	
    public void addItem(Project i) {
    	projectService.addItem(i);
    }

    public Project findItem(Integer id) {
        return projectService.findItem(id);
    }

    public void deleteItem(Project i) {
    	projectService.deleteItem(i);
    }

    public void updateItem(Project i) {
    	projectService.updateItem(i);
    }
    
    public Collection<Project> findAllProject()
    {
    	return projectService.findAllProject();
    }
    
    public Collection<Project> findAllProject(String username)
    {
    	Employee emp=employeeService.findItem(username);
    	Collection<Project> collec=  projectService.findAllProject();
    	Collection<Project> collec2 = new ArrayList<Project>();
    	collec2.addAll(collec);
    	for (Project project : collec) {
			if(!project.getEmployee().getIdemployee().equals(emp.getIdemployee()))
				collec2.remove(project);
		}
    	return collec2;
    }
    
    public Boolean checkChefDeProjet(Project p, Employee e) {
    	if(p.getEmployee().getIdemployee()==e.getIdemployee())
    		return true;
    	else 
    		return false;
    }
    
    public Collection<Project> getProjectTermine(Employee employee)
    {
    	Collection<Project> collec = new ArrayList<Project>();
    	for (Project p : this.findAllProject(employee.getEmail())) {
			if(this.estTermine(p))
				collec.add(p);
		}
    	return collec;
    }
    public Collection<Project> getProjectEnCours(Employee employee)
    {
    	Collection<Project> collec = new ArrayList<Project>();
    	for (Project p : this.findAllProject(employee.getEmail())) {
			if(!this.estTermine(p))
				collec.add(p);
		}
    	return collec;
    }
    public Boolean estTermine(Project project)
    {
    	Collection<Activity> collec =project.getActivities();
    	for(Activity a: collec)
    	{
    		if(a.getEstTermine()==0)
    		{
    			return false;
    		}
    	}
    	if(collec.size()==0)
    		return false;
    	
    	return true;
    }
    public int getNombreHeuresUtilisateurSurProjet(Employee e,Project project)
    {
    	Collection<Activity> collec =project.getActivities();
    	if(collec.size()==0)
    		return 0;
    	
    	int somme=0;
    	for(Activity a: collec)
    	{
    		if(a.getEmployee().getIdemployee()==e.getIdemployee())
    		{
    			somme=somme+this.avancementService.getSumByActivity(a.getIdactivity());
    		}
    	}
    	return somme;
	
    }
    


}
