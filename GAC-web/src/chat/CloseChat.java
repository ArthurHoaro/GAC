package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.FacesContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;


import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;

import remote.FConversationServicesRemote;
import remote.FEmployeeServicesRemote;
import remote.FMessageServicesRemote;

import model.Conversation;
import model.Employee;
import model.Message;

@ManagedBean(name="closeChat")
@RequestScoped
public class CloseChat {

	// Properties ---------------------------------------------------------------------------------
	@EJB
	private FConversationServicesRemote fcs; 
	@EJB
    private FEmployeeServicesRemote fes; 
	
	private Employee currentEmp;
	private Conversation convers;
	
	// Actions ------------------------------------------------------------------------------------
	
	public void init() {		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();	
		
		// If the user is logged in
		if( userSession.get("username") != null ) {
			currentEmp = fes.findItem((String) userSession.get("username"));
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
		
		try {
			String GET = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("convers");
			// If the contact is defined
			if( GET != null ) {
				convers = fcs.findItem(Integer.parseInt(GET));   
			}
			// Else : user not found
			else throw new Exception();
		
			// If the current employee is allowed to accept
			if( convers.getEmployeeByCalledIdemployee().getIdemployee() == currentEmp.getIdemployee()
					|| convers.getEmployeeByCallerIdemployee().getIdemployee() == currentEmp.getIdemployee()) {
				
				if( convers.getDayTimeEnd() == null ) {
					convers.setDayTimeEnd(new Date());
				} 
				else throw new Exception();
				
				fcs.updateItem(convers);
				
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(
							FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
							"/chat/convers.xhtml"
						);
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
			else throw new Exception();
		}
		catch( Exception e) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
						"/chat/infos/talking-notfound.xhtml"
					);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

	
	// Getters/setters ----------------------------------------------------------------------------
	
	public Employee getCurentEmp() {
		return currentEmp;
	}

	public void setCurentEmp(Employee curentEmp) {
		this.currentEmp = curentEmp;
	}

	public Conversation getConvers() {
		return convers;
	}

	public void setConvers(Conversation convers) {
		this.convers = convers;
	}	
	
	
	
	
	
}
