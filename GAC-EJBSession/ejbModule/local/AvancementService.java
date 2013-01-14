package local;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Avancement;


/**
 * Session Bean implementation class ActivityService
 */
@Stateless
public class AvancementService implements AvancementServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
    private EntityManager em;
    public void addItem(Avancement i) {
        em.persist(i);
    }

    public Avancement findItem(Integer id) {
        return em.find(Avancement.class, id);
    }

    public void deleteItem(Avancement i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Avancement i) {
        em.merge(i);
    }
    


}