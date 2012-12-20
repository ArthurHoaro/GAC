package local;

import javax.ejb.Local;

import model.Conversation;

@Local
public interface ConversationServiceLocal {

	  public void addItem(Conversation i);

	    public Conversation findItem(Integer id);

	    public void deleteItem(Conversation i);

	    public void updateItem(Conversation i);
	
}
