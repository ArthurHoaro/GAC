package account;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import remote.FEmployeeServicesRemote;

@ManagedBean
@RequestScoped
public class UserProfile {
	
	@EJB
    private FEmployeeServicesRemote fes; 
	
	// Return true if the CURRENT user is logged in -- Can't be used for other users.
	private boolean isLoggedIn;
	private String username;
	private String test;
	
	public void init() {		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		if( ! userSession.isEmpty() ) {
			this.username = (String) userSession.get("username");
			this.isLoggedIn = true;			
		}
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
