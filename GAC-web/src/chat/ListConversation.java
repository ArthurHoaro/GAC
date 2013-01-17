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

@ManagedBean(name="listConvers")
@RequestScoped
public class ListConversation {

	// Properties ---------------------------------------------------------------------------------
	@EJB
	private FConversationServicesRemote fcs; 
	@EJB
    private FEmployeeServicesRemote fes; 
	
	private Employee curentEmp;	
	private ArrayList<Conversation> listConvers;
	
	// Actions ------------------------------------------------------------------------------------
	
	public void init() {		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();	
		
		// If the user is logged in
		if( userSession.get("username") != null ) {
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
		
		
		this.listConvers = fcs.findItem(curentEmp);
		System.out.println("test");
	}
	
	
	// Getters/setters ----------------------------------------------------------------------------

	public Employee getCurentEmp() {
		return curentEmp;
	}

	public void setCurentEmp(Employee curentEmp) {
		this.curentEmp = curentEmp;
	}

	public ArrayList<Conversation> getListConvers() {
		return listConvers;
	}

	public void setListConvers(ArrayList<Conversation> listConvers) {
		this.listConvers = listConvers;
	}	
	
	
	
}
