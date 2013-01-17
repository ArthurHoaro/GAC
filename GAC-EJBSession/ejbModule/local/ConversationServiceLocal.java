package local;

import java.util.ArrayList;

import javax.ejb.Local;

import model.Conversation;
import model.Employee;

@Local
public interface ConversationServiceLocal {

	  public int addItem(Conversation i);

	    public ArrayList<Conversation> findItem(Employee currentEmp);

	    public void deleteItem(Conversation i);

	    public void updateItem(Conversation i);

		public Boolean conversExists(Employee currentEmp, Employee contact);

		public Conversation findItem(Integer id);
	
}
