package remote;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.ConversationServiceLocal;
import model.Conversation;
import model.Employee;

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

    public Conversation findItem(Integer id) {
        return conversationService.findItem(id);
    }

    public void deleteItem(Conversation i) {
    	conversationService.deleteItem(i);
    }

    public void updateItem(Conversation i) {
    	conversationService.updateItem(i);
    }

	@Override
	public int addItem(Employee currentEmp, Employee contact) throws Exception {
		//if( ! conversationService.conversExists(currentEmp, contact)) {
		if ( true ) {
			Conversation newConv = new Conversation();
			newConv.setEmployeeByCalledIdemployee(contact);
			newConv.setEmployeeByCallerIdemployee(currentEmp);
			newConv.setStatus(false);
			return conversationService.addItem(newConv);
		}
		else
			throw new Exception();
	}

	@Override
	public ArrayList<Conversation> findItem(Employee currentEmp) {
		return conversationService.findItem(currentEmp);
	} 
}
