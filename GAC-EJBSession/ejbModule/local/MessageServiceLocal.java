package local;

import javax.ejb.Local;

import model.Message;

@Local
public interface MessageServiceLocal {

	  public void addItem(Message i);

	    public Message findItem(long id);

	    public void deleteItem(Message i);

	    public void updateItem(Message i);
	
}
