package local;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Message;

/**
 * Session Bean implementation class MessageService
 */
@Stateless
public class MessageService implements MessageServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
    private EntityManager em;
    public void addItem(Message i) {
        em.persist(i);
    }

    public Message findItem(long id) {
        return em.find(Message.class, id);
    }

    public void deleteItem(Message i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Message i) {
        em.merge(i);
    }

}
