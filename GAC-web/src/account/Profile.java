package account;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import model.Avancement;
import model.Employee;
import model.Project;

import remote.FEmployeeServicesRemote;
import remote.FProjectServicesRemote;

@ManagedBean
@RequestScoped
public class Profile {

	
	// Properties ---------------------------------------------------------------------------------
	
	@EJB
    private FEmployeeServicesRemote fes; 
	@EJB
    private FProjectServicesRemote fps; 
	
	
	private Employee displayedEmp;
	private Employee currentEmp;

	
	// Actions ------------------------------------------------------------------------------------
	
	public void init() {		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();	
		
		// If the user is logged in
		if( userSession.get("username") != null) {
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
		
		String GET = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("user");
		// If the conversation is defined
		if( GET != null ) {
			this.displayedEmp = fes.findItem(Integer.parseInt(GET));   
		}
		
		if( this.displayedEmp == null ) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
						"/profile/infos/user-notfound.xhtml"
					);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	// Getters/setters ----------------------------------------------------------------------------
	
	public Employee getDisplayedEmp() {
		return displayedEmp;
	}


	public void setDisplayedEmp(Employee displayedEmp) {
		this.displayedEmp = displayedEmp;
	}


	public Employee getCurrentEmp() {
		return currentEmp;
	}


	public void setCurrentEmp(Employee currentEmp) {
		this.currentEmp = currentEmp;
	}	
	
	public Collection<Project>  getProjetEnCours() {
		return fps.getProjectEnCours(this.displayedEmp);
	}
	
	public Collection<Project>  getProjetTermine() {
		return fps.getProjectTermine(this.displayedEmp);
	}
	
	public String getManagerName(Project project) {
		Employee employee=project.getEmployee();
		return employee.getFirstname()+" "+employee.getLastname();
	}
	public int getSommeHeuresSurProjet(Project p) {
		return fps.getNombreHeuresUtilisateurSurProjet(this.displayedEmp, p);
	}
}
