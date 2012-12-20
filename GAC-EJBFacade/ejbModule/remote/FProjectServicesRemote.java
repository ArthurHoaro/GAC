package remote;

import javax.ejb.Remote;

import model.Project;

@Remote
public interface FProjectServicesRemote {
	 public void addItem(Project i);

	    public Project findItem(Integer id) ;

	    public void deleteItem(Project i) ;

	    public void updateItem(Project i) ;
}
