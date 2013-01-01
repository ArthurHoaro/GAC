package local;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Employee;
import model.Project;

/**
 * Session Bean implementation class EmployeeService
 */
@Stateless
public class EmployeeService implements EmployeeServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
    private EntityManager em;
    public void addItem(Employee i) {
        em.persist(i);
    }

    @Override
	public void addItem(String email, String lastname, String firstname,
			String password) {
		Employee emp = new Employee(email, password, firstname, lastname, null, new Date(), null, null, null, null, null);
		this.addItem(emp);
	}

	public Employee findItem(Integer id) {
        return em.find(Employee.class, id);
    }

    public void deleteItem(Employee i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Employee i) {
        em.merge(i);
    }

	@Override
	public Employee findItem(String email) {
		String str = "SELECT e FROM Employee e WHERE email = :email";
		Employee out;
		try {
			out = (Employee) em.createQuery(str).setParameter("email", email).getSingleResult();
		}
		catch (Exception ex) {
			out = null;
		}
		
		return out;
	}
	
    public Collection<Employee> findAllEmployee() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>) query.getResultList();
    }

}
