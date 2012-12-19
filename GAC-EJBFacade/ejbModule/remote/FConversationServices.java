package remote;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.ConversationServiceLocal;
import model.Conversation;

/**
 * Session Bean implementation class FConversationServices
 */
@Stateless
public class FConversationServices implements FConversationServicesRemote {

    /**
     * Default constructor. 
     */
	@EJB
    private ConversationServiceLocal conversationService;
    public void addItem(Conversation i) {
    	conversationService.addItem(i);
    }

    public Conversation findItem(long id) {
        return conversationService.findItem(id);
    }

    public void deleteItem(Conversation i) {
    	conversationService.deleteItem(i);
    }

    public void updateItem(Conversation i) {
    	conversationService.updateItem(i);
    }
 
}
