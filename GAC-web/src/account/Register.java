package account;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import model.Employee;

import remote.FEmployeeServicesRemote;

@ManagedBean
@RequestScoped
public class Register {
	// Properties ---------------------------------------------------------------------------------

	@EJB
    private FEmployeeServicesRemote fes; 
	
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    
    // Actions ------------------------------------------------------------------------------------

    public void init() {
    	if( ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("msg") 
    			.equals("register") )
    		FacesContext.getCurrentInstance().addMessage(null, 
    				new FacesMessage("L'utilisateur a bien été créé."));
    	
    }
    
    public void submit() {
    	if( email != null ) {
    		Employee emp = fes.findItem(email);
    		// Username (email) available
    		if( emp == null ) {
    			fes.addItem(email, lastname, firstname, password);
    			try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(
							FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
							"/login.xhtml?msg=register"
						);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		// User already registered
    		else {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cet utilisateur est déjà enregistré sur le site."));
    		}
    	}
    	else {
    		// Fields missing
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tous les champs doivent être remplis."));
    	}
    }



	// Getters/setters ----------------------------------------------------------------------------
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
