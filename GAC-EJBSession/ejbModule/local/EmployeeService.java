package local;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;

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

}
