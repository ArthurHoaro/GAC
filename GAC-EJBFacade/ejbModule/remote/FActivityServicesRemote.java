package remote;

import javax.ejb.Remote;

import model.Activity;

@Remote
public interface FActivityServicesRemote {
	 public void addItem(Activity i);

	    public Activity findItem(long id) ;

	    public void deleteItem(Activity i) ;

	    public void updateItem(Activity i) ;
}
