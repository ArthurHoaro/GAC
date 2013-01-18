package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import model.Activity;
import model.Employee;
import model.Project;

import remote.FEmployeeServicesRemote;
import remote.FProjectServicesRemote;



@ManagedBean
@SessionScoped
public class ProjetMB {
	
	
	 @EJB
	 private FProjectServicesRemote fps;
	 @EJB
	 private FEmployeeServicesRemote fes;	 
	 
	 private Project project;
	 private ArrayList<Activity> myActivityList;
	 private ArrayList<Activity> otherActivityList;
	 private Employee curentEmp;
	 private double avancement=0.0;	
	 private boolean modifMode = false;
	 private List<SelectItem> employeeOptions = new ArrayList<SelectItem>();
	 
	public ProjetMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() {	
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();		
		// If the user is logged in
		if( ! userSession.isEmpty() ) {
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
		
		
		String GET = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("project");
		// If project id defined
		int error=0;
		if(GET != null || project!=null ) {
			if(project == null)
				project = fps.findItem(Integer.parseInt(GET));		
			myActivityList=new ArrayList<Activity>();
			otherActivityList=new ArrayList<Activity>();
			for(Activity act : project.getActivities()){
				if(curentEmp!=null && (act.getEmployee().getEmail().equals(curentEmp.getEmail())) )
					myActivityList.add(act);
				else
					otherActivityList.add(act);
			}
			int termine=0;
			for(Activity act:project.getActivities())
				if(act.getEstTermine() == 1)
					termine++;	
			
			int total= project.getActivities().size();
			avancement= (double)termine/total;
			avancement= avancement*100;
		}		
		else
			error=1;		
		if(error>0){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
						"/chat/infos/talking-notfound.xhtml"
					);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getAvancement(){
		return (int) this.avancement;
	}

	public String getName(){
		return project.getName();
	}
	public void setName(String name){
		project.setName(name);		
	}

	public String getDescription(){
		return project.getDescription();
	}
	
	public void setDescription(String desc){
		project.setDescription(desc);		
	}
	
	
	public Integer getBudget(){
		return project.getBudject();
	}
	
	public void setBudget(Integer budget){
		this.project.setBudject(budget);		
	}
	
	public Employee getManager(){
		return project.getEmployee();
	}
	
	public void setManager(Employee e){
		this.project.setEmployee(e);
	}
	
	public List<SelectItem> getEmployeeOptions() {
    	List<Employee> liste = new ArrayList<Employee>();
    	for(Employee e : fes.findAllEmployee()){
    		employeeOptions.add(new SelectItem(e, e.getFirstname()+" "+e.getLastname()));
    	}
        return employeeOptions;
    }
	
	public ArrayList<Activity> getMyActiviyList(){
		return myActivityList;
	}
	public ArrayList<Activity> getOtherActiviyList(){
		return otherActivityList;
	}	
	public void toggleModif(boolean boo){
		this.modifMode =boo;	
	}
	
	public boolean isCurEmpManager(){
		boolean ret=false;
		if(curentEmp!=null && curentEmp.getEmail().equals(this.project.getEmployee().getEmail()))
			ret=true;
		return ret;
	}
	
	public int getProjectId(){
		return project.getIdproject();
	}
	
	public boolean editable(){
		boolean ret= false;
		if(isCurEmpManager() && modifMode )
			ret=true;
		return ret;
	}
	
	public void validerModification(){	
		fps.updateItem(project); toggleModif(false);
		
	}

	public void annulation(){
		toggleModif(false);
	}
}
