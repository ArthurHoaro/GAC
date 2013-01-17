

package project;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import model.Activity;
import model.Avancement;
import model.Employee;
import model.Project;


import remote.FActivityServicesRemote;
import remote.FEmployeeServicesRemote;
import remote.FProjectServicesRemote;



@ManagedBean
@SessionScoped
public class NewProjectMB {
	
	// Properties ---------------------------------------------------------------------------------
	
	
	 @EJB
	 private FActivityServicesRemote fas;
	@EJB
	 private FEmployeeServicesRemote fes;
	@EJB
	 private FProjectServicesRemote fps;
	

	 
	
	private List<SelectItem> employeeOptions = new ArrayList<SelectItem>();
	private int idEmployee;
	private String description;
	private int budgect;
	private String name;
	private Project project;
	private Employee curentEmp;
	
	// Actions ------------------------------------------------------------------------------------
	
	public NewProjectMB() {


	}

	public void init() {
		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		// If the user is logged in
		if( userSession.get("username")!=null ) {
			this.curentEmp = fes.findItem((String) userSession.get("username"));
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
		
		
	
	}
	
	public void createProject(){				
			this.project=new Project();
			this.project.setEmployee(this.curentEmp);
			this.project.setBudject(this.budgect);
			this.project.setDescription(this.description);
			this.project.setName(name);
			fps.addItem(project);

		  
		  	//redirection vers la page projet
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/project-created.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		 this.name=name;
	}
	public int getBudgect() {
		return this.budgect;
	}

	public void setBudgect(int budget) {
		this.budgect = budgect;
	}

	public String getDescription() {
		return description;
	}
	
	

	public void setDescription(String description) {
		this.description = description;
	}
	

    
	
}