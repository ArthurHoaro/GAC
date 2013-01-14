package remote;

import java.util.Collection;

import javax.ejb.Remote;

import model.Employee;

@Remote
public interface FEmployeeServicesRemote {
	 	public void addItem(Employee i);
	 
	 	public void addItem(String email, String lastname, String firstname, String password);

	    public Employee findItem(Integer id) ;
	    
	    public Employee findItem(String email) ;

	    public void deleteItem(Employee i) ;

	    public void updateItem(Employee i) ;
	    
	    public Collection<Employee> findAllEmployee();
	    
}
