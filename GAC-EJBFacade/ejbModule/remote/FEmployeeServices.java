package remote;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.EmployeeServiceLocal;
import model.Employee;

/**
 * Session Bean implementation class FEmployeeServices
 */
@Stateless
public class FEmployeeServices implements FEmployeeServicesRemote {

    /**
     * Default constructor. 
     */
	@EJB
    private EmployeeServiceLocal employeeService;
    public void addItem(Employee i) {
    	employeeService.addItem(i);
    }

    public Employee findItem(Integer id) {
        return employeeService.findItem(id);
    }

    public void deleteItem(Employee i) {
    	employeeService.deleteItem(i);
    }

    public void updateItem(Employee i) {
    	employeeService.updateItem(i);
    }

	@Override
	public Employee findItem(String email) {
		return employeeService.findItem(email);
	}
 
}
