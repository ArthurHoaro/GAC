package remote;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.EmployeeService;
import local.EmployeeServiceLocal;
import local.ProjectServiceLocal;
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
}
