package managedBean;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import model.Project;

import remote.FProjectServicesRemote;

@ManagedBean
@SessionScoped
public class UnifiedMB {

	@EJB
	private FProjectServicesRemote fps;
	
	public UnifiedMB() {
		// TODO Auto-generated constructor stub
	}
	
	public Collection<Project> getProjectsName()
	{
		return fps.findAllProject();
	}
	
	public String getName(){
		return fps.findItem(1).getName();
	}
}
