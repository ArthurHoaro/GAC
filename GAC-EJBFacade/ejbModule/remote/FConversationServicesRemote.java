package remote;

import javax.ejb.Remote;

import model.Conversation;

@Remote
public interface FConversationServicesRemote {
	 public void addItem(Conversation i);

	    public Conversation findItem(long id) ;

	    public void deleteItem(Conversation i) ;

	    public void updateItem(Conversation i) ;
}
