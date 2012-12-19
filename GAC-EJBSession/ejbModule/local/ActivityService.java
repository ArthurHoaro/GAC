package local;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Activity;


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

    public Activity findItem(long id) {
        return em.find(Activity.class, id);
    }

    public void deleteItem(Activity i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Activity i) {
        em.merge(i);
    }

}
