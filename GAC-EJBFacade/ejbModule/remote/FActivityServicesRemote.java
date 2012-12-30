package remote;

import javax.ejb.Remote;

import model.Activity;

@Remote
public interface FActivityServicesRemote {
	 public void addItem(Activity i);

	    public Activity findItem(Integer id) ;

	    public void deleteItem(Activity i) ;

	    public void updateItem(Activity i) ;
	    
	    public void ajouterCharge(Integer id, Integer chargeAAjouter);
	    
	    public String findItemProjectName(Integer id);
	    
	    public String findItemEmployeeName(Integer id);
}
