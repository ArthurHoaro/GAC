package local;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public Project findItem(Integer id) {
        return em.find(Project.class, id);
    }

    public void deleteItem(Project i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Project i) {
        em.merge(i);
    }
    
    public Collection<Project> findAllProject() {
        Query query = em.createQuery("SELECT e FROM Project e");
        return (Collection<Project>) query.getResultList();
    }
}
