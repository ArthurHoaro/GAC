package local;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Avancement;
import model.Employee;
import model.Project;


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
    
    
    public Collection<Avancement> getAvancementsByActivity(Integer id)
    {
        //Query query = em.createQuery("SELECT a FROM Avancement a WHERE idavancement= :id").setParameter("id", id);
    	Query query = em.createQuery("SELECT a FROM Avancement a ORDER BY idavancement DESC");
    	Collection<Avancement> liste = new ArrayList<Avancement>();
    	for(Avancement a : (Collection<Avancement>) query.getResultList()){
    		if(a.getActivityIdactivity()==id)
    			liste.add(a);
    	}
        return liste;
    }
    
    public int getSumByActivity(Integer id)
    {
    	int sum=0;
    	for(Avancement a : this.getAvancementsByActivity(id)){
    		sum=sum+a.getNbrHeures();
    	}
        return sum;
    }
    

}