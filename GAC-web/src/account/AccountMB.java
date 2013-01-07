package account;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import remote.FEmployeeServicesRemote;
import model.Employee;

@ManagedBean
@RequestScoped
public class AccountMB {
	// Properties ---------------------------------------------------------------------------------

	@EJB
    private FEmployeeServicesRemote fes; 
	
    private String oldpwd;
    private String newpwd;
    private String newpwdbis;
    
    private HtmlOutputText messageSuccess;
    private HtmlOutputText messageError;
    
    // Actions ------------------------------------------------------------------------------------

    public void changepwd() {
    	if( newpwd != null && newpwd.equals(newpwdbis) ) {
    		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    		
    		if( ! userSession.isEmpty() ) {
    			Employee emp = fes.findItem((String) userSession.get("username"));
    			if( emp.getPassword().equals(oldpwd) ) {
    				emp.setPassword(newpwd);
    				fes.updateItem(emp);
    			}
    			else
    				this.messageError.setValue("Mot de passe incorrect.");
    		}
    	}
    	else {
    		this.messageError.setValue("Les deux mots de passe ne correspondent pas.");
    	}
    }
    
	// Getters/setters ----------------------------------------------------------------------------
    
	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getNewpwdbis() {
		return newpwdbis;
	}

	public void setNewpwdbis(String newpwdbis) {
		this.newpwdbis = newpwdbis;
	}

	public HtmlOutputText getMessageSuccess() {
		return messageSuccess;
	}

	public void setMessageSuccess(HtmlOutputText messageSuccess) {
		this.messageSuccess = messageSuccess;
	}

	public HtmlOutputText getMessageError() {
		return messageError;
	}

	public void setMessageError(HtmlOutputText messageError) {
		this.messageError = messageError;
	}
	
}
