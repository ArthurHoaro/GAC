package remote;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.ActivityServiceLocal;
import local.AvancementServiceLocal;
import local.EmployeeServiceLocal;
import model.Activity;
import model.Avancement;
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
	@EJB
	private AvancementServiceLocal avancementService;
	
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
    public void ajouterCharge(Integer idActivity, Integer chargeAAjouter, Integer idEmployee)
    {
    	Avancement a=new Avancement();
    	a.setNbrHeures(chargeAAjouter);
    	a.setActivityIdactivity(idActivity);
    	a.setEmployeeIdemployee(idEmployee);
    	a.setDateEntry(new Date());
    	avancementService.addItem(a);
    	
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
    
    public int getNombreHeures(Integer id) {
    	return avancementService.getSumByActivity(id);
    	
    }
    
    public Collection<Avancement> getAllAvancementByActivity(Integer id) {
    	return avancementService.getAvancementsByActivity(id);
    }
 
}
