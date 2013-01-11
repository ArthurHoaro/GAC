package chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import remote.FConversationServicesRemote;
import remote.FEmployeeServicesRemote;
import remote.FMessageServicesRemote;

import model.Conversation;
import model.Employee;
import model.Message;

@ManagedBean
@RequestScoped
public class Talking {

	// Properties ---------------------------------------------------------------------------------
	@EJB
	private FMessageServicesRemote fms; 
	@EJB
	private FConversationServicesRemote fcs; 
	@EJB
    private FEmployeeServicesRemote fes; 
	
	private ArrayList<Message> listMessages;
	private Employee curentEmp;
	private Employee contact;
	private Conversation convers;

	
	// Actions ------------------------------------------------------------------------------------
	
	public void init() {		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();	
		
		// If the user is logged in
		if( ! userSession.isEmpty() ) {
			curentEmp = fes.findItem((String) userSession.get("username"));
		}
		// Isn't logged in, redirect to login page
		else {
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
		
		int error = 0;		
		
		try {
    		String GET = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("conversation");
    		// If the conversation is defined
    		if( GET != null ) {
    			convers = fcs.findItem(Integer.parseInt(GET));
    			if( convers != null) {
    				Employee called = convers.getEmployeeByCalledIdemployee();
    				Employee caller = convers.getEmployeeByCallerIdemployee();
    				
    				// If the caller is the curent employee then contact is the called employee
    				if( called.getIdemployee() == curentEmp.getIdemployee() ) {
    					contact = caller;
    				}
    				// The other way around
    				else if ( caller.getIdemployee() == curentEmp.getIdemployee() ) {
    					contact = called;
    				}
    				else error = 1;
    			} else error = 2;
    		}
    		else
    			error = 3;
    		
    		// An error occurred => 404 conversation
    		if( error > 0 ) {
    			try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(
							FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
							"/chat/infos/talking-notfound.xhtml"
						);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else {
    			listMessages = fms.getMessagesByIdConversation(convers.getIdconversation());
    		}
    	}
    	catch( NullPointerException e ) {
    		;
    	}
		
	}

	// Getters/setters ----------------------------------------------------------------------------
	public ArrayList<Message> getListMessages() {
		return listMessages;
	}


	public void setListMessages(ArrayList<Message> listMessages) {
		this.listMessages = listMessages;
	}
	
}
