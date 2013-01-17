package remote;

import java.util.Collection;

import javax.ejb.Remote;

import model.Employee;
import model.Project;

@Remote
public interface FProjectServicesRemote {
	 public void addItem(Project i);

	    public Project findItem(Integer id) ;

	    public void deleteItem(Project i) ;

	    public void updateItem(Project i) ;
	    
	    public Collection<Project> findAllProject();
	    
	    public Boolean checkChefDeProjet(Project p, Employee e);

	    public Collection<Project> findAllProject(String username);
	        
	    public Collection<Project> getProjectEnCours(Employee employee);
	    
	    public Collection<Project> getProjectTermine(Employee employee);
	    
	    public Boolean estTermine(Project project);
	    
	    public int getNombreHeuresUtilisateurSurProjet(Employee e,Project project);
}
