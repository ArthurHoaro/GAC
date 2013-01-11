package remote;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.ActivityServiceLocal;
import local.EmployeeServiceLocal;
import model.Activity;
import model.Employee;
import model.Project;

/**
 * Session Bean implementation class FActivityServices
 */
@Stateless
public class FActivityServices implements FActivityServicesRemote {

    /**
     * Default constructor. 
     */
	@EJB
    private ActivityServiceLocal activityService;
	@EJB
	private EmployeeServiceLocal employeeService;
	
    public void addItem(Activity i) {
    	activityService.addItem(i);
    }

    public Activity findItem(Integer id) {
        return activityService.findItem(id);
    }

    public void deleteItem(Activity i) {
    	activityService.deleteItem(i);
    }

    public void updateItem(Activity i) {
    	activityService.updateItem(i);
    }
    public void ajouterCharge(Integer id, Integer chargeAAjouter)
    {
    	activityService.ajouterCharge(id,chargeAAjouter);
    }
    public String findItemProjectName(Integer id)
    {
    	Project p =activityService.findItem(id).getProject();
    	return p.getName();
    }
    public String findItemEmployeeName(Integer id)
    {
    	Employee e =activityService.findItem(id).getEmployee();
    	return e.getFirstname()+" "+e.getLastname();
    }
    
    public void modifierEmployee(Integer id, Integer idEmployee)
    {
    	Activity a=activityService.findItem(id);
    	Employee e=employeeService.findItem(idEmployee);
    	a.setEmployee(e);
    	activityService.updateItem(a);
    }
 
}
