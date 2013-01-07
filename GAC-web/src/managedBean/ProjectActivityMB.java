

package managedBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.faces.FacesException;
import javax.faces.model.SelectItem;

import model.Employee;

import remote.FActivityServicesRemote;
import remote.FEmployeeServicesRemote;
import remote.FProjectServicesRemote;



@ManagedBean
@SessionScoped
public class ProjectActivityMB {
	
	// Properties ---------------------------------------------------------------------------------
	
	 @EJB
	 private FActivityServicesRemote fas;
	 private FProjectServicesRemote fps;
	 private FEmployeeServicesRemote fes;
 
	private int idActivity;
	private int idEmployee;
	private int chargeAAjouter=0;
	private int modif=0;
	private String modifMode="false";
	private String readMode="true";
	private HtmlOutputText employeeNameOutputText=new HtmlOutputText();
	private List<SelectItem> employeeOptions = new ArrayList<SelectItem>();
	
	
	// Actions ------------------------------------------------------------------------------------
	
	public ProjectActivityMB() {
		this.init();

	}

	public void init() {
		//on recupere l' id de l'activit� pass� par l url 
		String 	idActivityString = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idActivity");
	
		//on recupere l'id de l'employe connect�
		idEmployee=1;
		
		//on v�rifie l id r�cup�r�
		if(idActivityString!= null && idActivityString!="") {
			idActivity=Integer.parseInt(idActivityString);
		} else {
			idActivity=1;
		}

	   //TO DO verifier si l'utilisateur est chef de projet pour pouvoir modifier
		
	}
		
	public void modifierEmployee(){
		this.modifMode="true";
		this.readMode="false";
		
	}
	public void validerModificationEmployee(){
		
	}
	public void annulerModificationEmployee(){
		this.modifMode="false";
		this.readMode="true";
	}
	public void ajouterCharge(){
		fas.ajouterCharge(this.idActivity, this.chargeAAjouter);
	}
	public String getProjectName(){
		return fas.findItemProjectName(this.idActivity);
	}
	public String getEmployeeName(){
		return fas.findItemEmployeeName(this.idActivity);
	}
	public int getActivityCharge(){
		return fas.findItem(this.idActivity).getCharge();
	}
	
	public int getChargeAAjouter(){
		return this.chargeAAjouter;
	}
	
	public void setChargeAAjouter(int chargeAAjouter){
		 this.chargeAAjouter= chargeAAjouter;
	}	
	
	public void setEmployeeNameOutputText(HtmlOutputText employeeNameOutputText){
		 this.employeeNameOutputText= employeeNameOutputText;
	}	
	public HtmlOutputText getEmployeeNameOutputText() {
		return this.employeeNameOutputText;
	}
	public String getModifMode() {
		return this.modifMode;
	}
	public String getReadMode() {
		return this.readMode;
	}
	
	//construit une list de "select items" a partir de la liste des employ�s
    public List<SelectItem> getEmployeeOptions() {
    	SelectItem s;
    	//Iterator i=fes.findAllEmployee().iterator();
    	
		/*while(i.hasNext()) {
			
			s=new SelectItem(fes.findAllEmployee()[],e.getFirstname()+ " "+e.getLastname());
			employeeOptions.add(s);
		}*/
		
        return employeeOptions;
    }
	
}