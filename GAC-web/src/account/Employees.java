package account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import remote.FEmployeeServicesRemote;

import model.Employee;

@ManagedBean
@RequestScoped
public class Employees {
	// Properties ---------------------------------------------------------------------------------

	@EJB
    private FEmployeeServicesRemote fes; 
    
	private ArrayList<Employee> listEmployee;
	private Employee currentEmp;
    
    // Actions ------------------------------------------------------------------------------------

	public void init() {
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			
		// If the user is logged in
		if( userSession.get("username") != null) {
			currentEmp = fes.findItem((String) userSession.get("username"));
			
			listEmployee = (ArrayList<Employee>) fes.findAllEmployee();
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
	}
    
	// Getters/setters ----------------------------------------------------------------------------
    
    
	public ArrayList<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(ArrayList<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public Employee getCurrentEmp() {
		return currentEmp;
	}

	public void setCurrentEmp(Employee currentEmp) {
		this.currentEmp = currentEmp;
	}
}
