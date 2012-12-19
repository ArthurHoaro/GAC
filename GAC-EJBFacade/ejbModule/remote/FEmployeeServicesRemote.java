package remote;

import javax.ejb.Remote;

import model.Employee;

@Remote
public interface FEmployeeServicesRemote {
	 public void addItem(Employee i);

	    public Employee findItem(Integer id) ;

	    public void deleteItem(Employee i) ;

	    public void updateItem(Employee i) ;
}
