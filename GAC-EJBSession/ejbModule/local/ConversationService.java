package local;

import java.util.ArrayList;

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
    public int addItem(Conversation i) {
        em.persist(i);
        return i.getIdconversation();
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
	
	public Boolean conversExists(Employee currentEmp, Employee contact) {
		ArrayList<Conversation> out = new ArrayList<Conversation>();
		String str = "SELECT c " +
				"FROM Conversation c " +
				"WHERE " +
					"( caller_idemployee = :current OR caller_idemployee = :contact ) " +
					"AND" +
					"( called_idemployee = :current OR called_idemployee = :contact ) " +
					"AND called_idemployee <> caller_idemployee " +
					"AND status = 1";
		
		try {
			out = (ArrayList<Conversation>) em.createQuery(str).setParameter("current", currentEmp.getIdemployee()).setParameter("contact", contact.getIdemployee()).getResultList();
		}
		catch (NullPointerException e) {
			return false;
		}
		catch (Exception ex) {
			ex.printStackTrace();			
		}
		return !out.isEmpty();
	}

	@Override
	public ArrayList<Conversation> findItem(Employee currentEmp) {
		ArrayList<Conversation> out = new ArrayList<Conversation>();
		String str = "SELECT c " +
				"FROM Conversation c " +
				"WHERE " +
					"caller_idemployee = :current OR called_idemployee = :current " +					
					"AND status = 1";		
		try {
			out = (ArrayList<Conversation>) em.createQuery(str).setParameter("current", currentEmp.getIdemployee()).getResultList();
		}
		catch (Exception ex) {
			ex.printStackTrace();			
		}
		return out;
	}

}
