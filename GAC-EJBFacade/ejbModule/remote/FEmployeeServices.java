package remote;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.EmployeeServiceLocal;
import model.Employee;
import model.Project;

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

    @Override
	public void addItem(String email, String lastname, String firstname, String password) {
    	employeeService.addItem(email, lastname, firstname, password);
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
    
    public Collection<Employee> findAllEmployee()
    {
    	return employeeService.findAllEmployee();
    }

	@Override
	public Employee findItem(String email) {
		return employeeService.findItem(email);
	}
	
	
 
}
