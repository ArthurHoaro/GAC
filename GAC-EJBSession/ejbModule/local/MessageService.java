package local;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
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

    public Message findItem(Integer id) {
        return em.find(Message.class, id);
    }

    public void deleteItem(Message i) {
        em.remove(em.merge(i)); //need to merge to reattach entity
    }

    public void updateItem(Message i) {
        em.merge(i);
    }

	@Override
	public ArrayList<Message> getMessagesByIdConversation(Integer id) {
		// TODO Auto-generated method stub
		String str = "SELECT m FROM Message m WHERE conversation_idconversation = :idConvers ORDER BY send_time ASC";
		ArrayList<Message> out;
		try {
			out = (ArrayList<Message>) em.createQuery(str).setParameter("idConvers", id).getResultList();
		}
		catch (Exception ex) {
			out = null;
		}
		
		return out;
	}

    
}
