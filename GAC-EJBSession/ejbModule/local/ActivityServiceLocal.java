package local;

import javax.ejb.Local;

import model.Activity;

@Local
public interface ActivityServiceLocal {

	  public void addItem(Activity i);

	    public Activity findItem(long id);

	    public void deleteItem(Activity i);

	    public void updateItem(Activity i);
	
}
