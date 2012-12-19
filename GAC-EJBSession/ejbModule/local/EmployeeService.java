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

}
