package remote;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.ProjectServiceLocal;
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
 
}
