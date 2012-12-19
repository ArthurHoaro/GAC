package local;

import javax.ejb.Local;

import model.Project;

@Local
public interface ProjectServiceLocal {

	  public void addItem(Project i);

	    public Project findItem(long id);

	    public void deleteItem(Project i);

	    public void updateItem(Project i);
	
}
