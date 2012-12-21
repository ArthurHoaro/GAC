package local;

import java.util.Collection;

import javax.ejb.Local;

import model.Project;

@Local
public interface ProjectServiceLocal {

	  	public void addItem(Project i);

	    public Project findItem(Integer id);

	    public void deleteItem(Project i);

	    public void updateItem(Project i);
	    
	    public Collection<Project> findAllProject();
	
}
