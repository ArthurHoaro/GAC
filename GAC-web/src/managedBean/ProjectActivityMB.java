

package managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import remote.FActivityServicesRemote;
import remote.FProjectServicesRemote;


@ManagedBean
@SessionScoped
public class ProjectActivityMB {
	
	
	 @EJB
	 private FActivityServicesRemote fas;
	 
	 
		private int idActivity;
		private int idEmployee;
		private int chargeAAjouter=2;

	public ProjectActivityMB() {
		//on recupere les parametres
		idActivity=10;
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
