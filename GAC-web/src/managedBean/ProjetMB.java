package managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import account.UserProfile;

import remote.FProjectServicesRemote;


@ManagedBean
@SessionScoped
public class ProjetMB {
	
	
	 @EJB
	 private FProjectServicesRemote fps;
	 @EJB
	 private UserProfile userProfile;
	 
	public ProjetMB() {
		// TODO Auto-generated constructor stub
	}

	public String getName(){		
		return fps.findItem(1).getName();
	}
	
}
