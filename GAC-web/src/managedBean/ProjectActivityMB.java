

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

	// Actions ------------------------------------------------------------------------------------
	
	public ProjectActivityMB() {
		
		//on recupere l' id de l'activité passé par l url 
		String 	idActivityString = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idActivity");
	
		//on vérifie l id récupéré
		if(idActivityString!= null && idActivityString!="") {
			idActivity=Integer.parseInt(idActivityString);
		} else {
			idActivity=1;
		}
		
		//on recupere l'id de l'employe connecté
		idEmployee=1;
	}
	public void ajouterCharge(){
		fas.ajouterCharge(this.idActivity, this.chargeAAjouter);
	}
	public String getProjectName(){
		return fas.findItemProjectName(this.idActivity);
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
