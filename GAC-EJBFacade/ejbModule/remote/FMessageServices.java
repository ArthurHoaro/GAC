package remote;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.MessageServiceLocal;
import model.Message;

/**
 * Session Bean implementation class FMessageServices
 */
@Stateless
public class FMessageServices implements FMessageServicesRemote {

    /**
     * Default constructor. 
     */
	@EJB
    private MessageServiceLocal messageService;
    public void addItem(Message i) {
    	messageService.addItem(i);
    }

    public Message findItem(Integer id) {
        return messageService.findItem(id);
    }

    public void deleteItem(Message i) {
    	messageService.deleteItem(i);
    }

    public void updateItem(Message i) {
    	messageService.updateItem(i);
    }
    
    public ArrayList<Message> getMessagesByIdConversation(Integer id) {
    	return messageService.getMessagesByIdConversation(id);
    }
 
}
