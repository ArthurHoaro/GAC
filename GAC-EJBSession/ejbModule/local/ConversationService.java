package local;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Conversation;

/**
 * Session Bean implementation class ConversationService
 */
@Stateless
public class ConversationService implements ConversationServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
    private EntityManager em;
    public void addItem(Conversation i) {
        em.persist(i);
    }

    public Conversation findItem(long id) {
        return em.find(Conversation.class, id);
    }

    public void deleteItem(Conversation i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Conversation i) {
        em.merge(i);
    }

}
