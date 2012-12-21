package account;

import java.io.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Employee;
import remote.FEmployeeServicesRemote;

@ManagedBean
@RequestScoped
public class LoginOld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    private FEmployeeServicesRemote fes; 
	
	
	private String username = new String();
	private String password = new String();
	
	private String debugString = "test";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if( request.getParameter("username") != null )
			this.username = request.getParameter("username");
		if( request.getParameter("password") != null )
			this.password = request.getParameter("password");
	}
	
	public void authenticate() {
		System.out.println(this.username.toString());
		if( this.username != "" && this.password != "") {
			try {
				Employee emp = fes.findItem(this.username);
				if( emp.getPassword() == this.password ) {
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
			}
			catch (NoResultException e) {
				// User doesnt exist
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=UNOF");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}			
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=FINC");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
