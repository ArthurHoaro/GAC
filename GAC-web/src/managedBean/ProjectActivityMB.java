package managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Activity;
import model.Project;

import remote.FActivityServicesRemote;
import remote.FProjectServicesRemote;


@ManagedBean
@SessionScoped
public class ProjectActivityMB {
	
	private int idActivity;
	private int idEmployee;
	private Activity activity;
	private Project project;
	 @EJB
	 private FActivityServicesRemote fas;
	 private FProjectServicesRemote fps;
	 
	 

	public ProjectActivityMB() {
		
		//on recupere les parametres
		idActivity=1;
		idEmployee=1;
		
		//on recupere l'activity
		activity=fas.findItem(idActivity);
		
		//on recupere le projet
		project=activity.getProject();
		
		
	}

	public String getProjectName(){
		return project.getName();
	}
	
	
	
}
