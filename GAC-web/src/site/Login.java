package site;

import java.io.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Employee;
import remote.FEmployeeServicesRemote;

@ManagedBean
@SessionScoped
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    private FEmployeeServicesRemote fes; 
	
	private String postBackUsername = new String();
	private String postBackPassword = new String();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if( request.getParameter("username") != null )
			this.postBackUsername = request.getParameter("username");
		if( request.getParameter("password") != null )
			this.postBackPassword = request.getParameter("password");
	}
	
	public void authenticate() {
		if( this.postBackUsername != "" && this.postBackPassword != "") {
			try {
				Employee emp = fes.findItem(this.postBackUsername);
				if( emp.getPassword() == this.postBackPassword ) {
					// Logged in
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put("username", this.postBackUsername);
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
					FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf?error=UNOF");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}			
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf?error=FINC");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
