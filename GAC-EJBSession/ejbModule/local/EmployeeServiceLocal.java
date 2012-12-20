package local;

import javax.ejb.Local;

import model.Employee;

@Local
public interface EmployeeServiceLocal {

	  public void addItem(Employee i);

	    public Employee findItem(Integer id);
	    
	    public Employee findItem(String email);

	    public void deleteItem(Employee i);

	    public void updateItem(Employee i);
	
}
