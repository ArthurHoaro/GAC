package account;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import remote.FEmployeeServicesRemote;

import model.Employee;

@ManagedBean
@RequestScoped
public class Login {
	// Properties ---------------------------------------------------------------------------------

	@EJB
    private FEmployeeServicesRemote fes; 
	
    private String username;
    private String password;
    
    // Actions ------------------------------------------------------------------------------------

    public void submit() {
        String message = String.format("Submitted: input1=%s, input2=%s", username, password);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
        
        // Fields filled
        if( this.username != "" && this.password != "") {
			Employee emp = fes.findItem(this.username);
			
			// User found in DB
			if( emp != null ) {
				if( emp.getPassword().equals(this.password) ) {
					// Logged in
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put("username", this.username);
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("logged-in.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// Wrong password
				else {
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("/GAC-web/login.xhtml?error=EPWD");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			// User doesn't exist
			else {
				// User doesnt exist
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=UNOF");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        }
        // Fields missing
        else
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message + " | empty"));
    }

	// Getters/setters ----------------------------------------------------------------------------
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
