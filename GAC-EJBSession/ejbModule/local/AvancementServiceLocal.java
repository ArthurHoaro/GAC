package local;

import java.util.Collection;

import javax.ejb.Local;

import model.Avancement;

@Local
public interface AvancementServiceLocal {

	  public void addItem(Avancement i);

	    public Avancement findItem(Integer id);

	    public void deleteItem(Avancement i);

	    public void updateItem(Avancement i);
	    
	    public Collection<Avancement> getAvancementsByActivity(Integer id);
	    
	    public int getSumByActivity(Integer id);
}