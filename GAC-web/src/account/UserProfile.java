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
@SessionScoped
public class UserProfile {
	
	@EJB
    private FEmployeeServicesRemote fes; 
	
	// Return true if the CURRENT user is logged in -- Can't be used for other users.
	private boolean isLoggedIn;
	private String username;
	private String test;
	private String firstname;
	private String lastname;	

	public void init() {		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		if( ! userSession.isEmpty() ) {
			this.username = (String) userSession.get("username");
			this.isLoggedIn = true;
			this.firstname = fes.findItem(username).getFirstname();
			this.lastname = fes.findItem(username).getLastname();
		}
		else 
			this.isLoggedIn = false;
	}

	public String getTest() {
		return "1. " + this.test;
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
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
