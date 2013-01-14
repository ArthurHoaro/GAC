package local;

import java.util.Collection;

import javax.ejb.Local;

import model.Employee;

@Local
public interface EmployeeServiceLocal {

	  public void addItem(Employee i);
	  public void addItem(String email, String lastname, String firstname, String password);

	    public Employee findItem(Integer id);
	    
	    public Employee findItem(String email);

	    public void deleteItem(Employee i);

	    public void updateItem(Employee i);
	    
	    public Collection<Employee> findAllEmployee();
	
	    
}
