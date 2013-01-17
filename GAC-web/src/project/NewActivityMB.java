

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
public class NewActivityMB {
	
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
	private int charge=0;
	private Project project;
	private int idProject=-1;
	private Employee curentEmp;
	

	// Actions ------------------------------------------------------------------------------------
	
	public NewActivityMB() {


	}

	public void init() {
		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		// If the user is logged in
		if( userSession.get("username")!=null ) {
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
		
		//on recupere l' id de l'activité passé par l url 
		String 	idProjectString = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProject");
	
		
		//on vérifie l id récupéré
		if(idProjectString!= null && idProjectString!="") {
			this.idProject=Integer.parseInt(idProjectString);
			this.project=fps.findItem(this.idProject);
		} else if(this.idProject == -1) {
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
						"/project-notfound.xhtml"
					);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		//on verifie si l'utilisateur est le chef de projet
		if(!this.checkChefDeProjet()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
						"/createActivityError.xhtml"
					);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createActivity(){
		  if( this.description != "" && this.charge >0 ) {
				Employee emp = fes.findItem(this.idEmployee);
				Activity a= new Activity();
				
				// User found in DB
				if( emp != null ) {
						a.setEmployee(fes.findItem(idEmployee));
						a.setCharge(this.charge);
						a.setEstTermine(0);
						a.setDescription(this.description);
						a.setProject(this.project);
						fas.addItem(a);
						
				}
				// User doesn't exist
				else {				
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rentrer une description et une charge"));				
				}
	        }
	        // Fields missing
	        else
	        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("empty"));
		  
		  	//redirection vers la page projet
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/project.xhtml?project="+this.project.getIdproject());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//construit une list de "select items" a partir de la liste des employés
    public List<SelectItem> getEmployeeOptions() {
    	List<Employee> liste = new ArrayList<Employee>();
    	for(Employee e : fes.findAllEmployee()){
    		employeeOptions.add(new SelectItem(e.getIdemployee(), e.getFirstname()+" "+e.getLastname()));
    	}
        return employeeOptions;
    }

    public Boolean checkChefDeProjet(){
    	return this.fps.checkChefDeProjet(this.project, this.curentEmp);
    }
	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getProjectName() {
		return this.project.getName();
	}
	

    
	
}
