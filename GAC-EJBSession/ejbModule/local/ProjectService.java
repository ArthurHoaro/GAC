package local;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Project;

/**
 * Session Bean implementation class ProjectService
 */
@Stateless
public class ProjectService implements ProjectServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
    private EntityManager em;
    public void addItem(Project i) {
        em.persist(i);
    }

    public Project findItem(long id) {
        return em.find(Project.class, id);
    }

    public void deleteItem(Project i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Project i) {
        em.merge(i);
    }

}
