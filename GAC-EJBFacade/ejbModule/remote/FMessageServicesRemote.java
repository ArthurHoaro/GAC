package remote;

import javax.ejb.Remote;

import model.Message;

@Remote
public interface FMessageServicesRemote {
	 public void addItem(Message i);

	    public Message findItem(long id) ;

	    public void deleteItem(Message i) ;

	    public void updateItem(Message i) ;
}
