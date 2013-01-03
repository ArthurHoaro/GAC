package account;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;


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
    
    private HtmlOutputText messageSuccess;
    
    // Actions ------------------------------------------------------------------------------------

    public void submit() {
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
						FacesContext.getCurrentInstance().getExternalContext().redirect(
								FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
								"/logged-in.xhtml"
							);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// Wrong password
				else {					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nom d'utilisateur ou mot de passe incorrect."));
				}
			}
			// User doesn't exist
			else {				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nom d'utilisateur ou mot de passe incorrect."));				
			}
        }
        // Fields missing
        else
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("empty"));
    }

    public void displayMessage() {
    	try {
    		String GET = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("msg");
    		switch( GET ) {
    			case "register":
    				this.messageSuccess.setValue("Votre utilisateur a bien été créé. Vous pouvez vous connecter.");    				
    			break;
    		}    			
    	}
    	catch( NullPointerException e ) {
    		;
    	}
    }
    
    public void logout() {
    	Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		if( ! userSession.isEmpty() ) {
    		userSession.clear();
    	}
    	
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
					"/login.xhtml"
				);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public HtmlOutputText getMessageSuccess() {
		return messageSuccess;
	}

	public void setMessageSuccess(HtmlOutputText messageSuccess) {
		this.messageSuccess = messageSuccess;
	}
}
