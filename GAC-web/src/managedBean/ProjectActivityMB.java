

package managedBean;

import java.util.ArrayList;
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

import remote.FActivityServicesRemote;
import remote.FProjectServicesRemote;



@ManagedBean
@SessionScoped
public class ProjectActivityMB {
	
	// Properties ---------------------------------------------------------------------------------
	
	 @EJB
	 private FActivityServicesRemote fas;
	 private FProjectServicesRemote fps;
 
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
		//on recupere l' id de l'activité passé par l url 
		String 	idActivityString = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idActivity");
	
		//on recupere l'id de l'employe connecté
		idEmployee=1;
		
		//on vérifie l id récupéré
		if(idActivityString!= null && idActivityString!="") {
			idActivity=Integer.parseInt(idActivityString);
		} else {
			idActivity=1;
		}
		
		//on verifie si l'activité est en mode modification
	   //TO DO verifier si l'utilisateur est chef de projet pour pouvoir modifier
		
		//on recupere le parametre "modif" passé par l url 
		String 	modifString = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("modif");
		
		//on vérifie l id récupéré
		if(modifString!= null && modifString!="") {
			
			if(Integer.parseInt(modifString)==1)
			{
				this.modifMode="true";
				this.readMode="false";
			} else { 
				this.modifMode="false";
				this.readMode="true";
			}
		} 
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
    public List<SelectItem> getEmployeeOptions() {
        return employeeOptions;
    }
	
}
