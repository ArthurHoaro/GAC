package local;

import java.util.ArrayList;

import javax.ejb.Local;

import model.Message;

@Local
public interface MessageServiceLocal {

	  public void addItem(Message i);

	    public Message findItem(Integer id);

	    public void deleteItem(Message i);

	    public void updateItem(Message i);
	
	    public ArrayList<Message> getMessagesByIdConversation(Integer id);
}
