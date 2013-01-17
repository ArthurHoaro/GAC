package local;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Conversation;
import model.Employee;

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

    public Conversation findItem(Integer id) {
        return em.find(Conversation.class, id);
    }

    public void deleteItem(Conversation i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Conversation i) {
        em.merge(i);
    }

	@Override
	public Conversation findItem(Employee contact, Employee currentEmp) {
		String str = "SELECT c FROM Conversation c WHERE email = :contact";
		Employee out;
		try {
			out = (Employee) em.createQuery(str).setParameter("email", email).getSingleResult();
		}
		catch (Exception ex) {
			out = null;
		}
		
		return out;
		return null;
	}

}
