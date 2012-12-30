

package managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;


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

	// Actions ------------------------------------------------------------------------------------
	
	public ProjectActivityMB() {
		
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
		
		//on verifie si l'activit� est en mode modification
	   //TO DO verifier si l'utilisateur est chef de projet pour pouvoir modifier
		
		//on recupere le parametre "modif" pass� par l url 
		String 	modifString = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("modif");
		
		//on v�rifie l id r�cup�r�
		/*if(Integer.parseInt(modifString)==1) {
	
		} else {
			
		}*/
		

		
		
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
	
}
