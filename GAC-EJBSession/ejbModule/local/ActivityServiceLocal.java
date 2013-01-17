package local;

import java.util.Collection;

import javax.ejb.Local;

import model.Activity;

@Local
public interface ActivityServiceLocal {

	  public void addItem(Activity i);

	    public Activity findItem(Integer id);

	    public void deleteItem(Activity i);

	    public void updateItem(Activity i);
	    
	    public Collection<Activity> findAllActivities();
;
	
}
