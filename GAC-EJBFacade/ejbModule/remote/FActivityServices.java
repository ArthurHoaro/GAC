package remote;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import local.ActivityServiceLocal;
import model.Activity;
import model.Project;

/**
 * Session Bean implementation class FActivityServices
 */
@Stateless
public class FActivityServices implements FActivityServicesRemote {

    /**
     * Default constructor. 
     */
	@EJB
    private ActivityServiceLocal activityService;
    public void addItem(Activity i) {
    	activityService.addItem(i);
    }

    public Activity findItem(Integer id) {
        return activityService.findItem(id);
    }

    public void deleteItem(Activity i) {
    	activityService.deleteItem(i);
    }

    public void updateItem(Activity i) {
    	activityService.updateItem(i);
    }
    public void ajouterCharge(Integer id, Integer chargeAAjouter)
    {
    	activityService.ajouterCharge(id,chargeAAjouter);
    }
    public String findItemProjectName(Integer id)
    {
    	Project p =activityService.findItem(id).getProject();
    	return p.getName();
    }
 
}
