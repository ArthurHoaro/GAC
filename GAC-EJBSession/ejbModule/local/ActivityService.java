package local;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Activity;
import model.Avancement;
import model.Employee;
import model.Project;


/**
 * Session Bean implementation class ActivityService
 */
@Stateless
public class ActivityService implements ActivityServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
    private EntityManager em;
    public void addItem(Activity i) {
        em.persist(i);
    }

    public Activity findItem(Integer id) {
        return em.find(Activity.class, id);
    }

    public void deleteItem(Activity i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Activity i) {
        em.merge(i);
    }

    public Collection<Activity> findAllActivities() {
        Query query = em.createQuery("SELECT e FROM Activity e");
        return (Collection<Activity>) query.getResultList();
    }

}
