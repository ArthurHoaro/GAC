

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

import javax.servlet.http.HttpServletRequest;


@ManagedBean
@SessionScoped
public class ProjectActivityMB {
	
	// Properties ---------------------------------------------------------------------------------
	
	 @EJB
	 private FActivityServicesRemote fas;
 
	private int idActivity;
	private int idEmployee;
	private int chargeAAjouter=0;

	// Actions ------------------------------------------------------------------------------------
	
	public ProjectActivityMB() {
		
		//on recupere l' id de l'activité passé par l url 
		if(true) {
			idActivity=10;
		} else {
			idActivity=0;
		}
		
		//on recupere l'id de l'employe connecté
		idEmployee=1;

	}
	public void ajouterCharge(){
		
		fas.ajouterCharge(this.idActivity, this.chargeAAjouter);
	}
	public String getProjectName(){
		return "pas encore implémenté";
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
