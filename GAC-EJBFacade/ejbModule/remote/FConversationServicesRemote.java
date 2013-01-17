package remote;

import java.util.ArrayList;

import javax.ejb.Remote;

import model.Conversation;
import model.Employee;

@Remote
public interface FConversationServicesRemote {
	 public void addItem(Conversation i);

	    public ArrayList<Conversation> findItem(Employee currentEmp) ;

	    public void deleteItem(Conversation i) ;

	    public void updateItem(Conversation i) ;

		public int addItem(Employee currentEmp, Employee contact) throws Exception;

		public Conversation findItem(Integer id);
}
