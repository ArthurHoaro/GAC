

package project;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import model.Activity;
import model.Avancement;
import model.Employee;
import model.Project;


import remote.FActivityServicesRemote;
import remote.FEmployeeServicesRemote;
import remote.FProjectServicesRemote;



@ManagedBean
@SessionScoped
public class ProjectActivityMB {
	
	// Properties ---------------------------------------------------------------------------------
	
	
	 @EJB
	 private FActivityServicesRemote fas;
	@EJB
	 private FEmployeeServicesRemote fes;
	@EJB
	 private FProjectServicesRemote fps;

	 
	
	private int idActivity;
	private int idEmployee;
	private int newIdEmployee;
	private int chargeAAjouter=0;
	private Project project;
	private Activity activity;
	private Employee employee;
	private boolean  modif=false;
	private String statut;
	private String activityDescription;
	private String modifMode="false";
	private String readMode="true";
	private String afficherFormulaireAjout="false";
	private String afficherBoutonModifier="false";
	private String afficherBoutonEnleverHeures="false";
	private String afficherBoutonTerminer="true";
	private HtmlOutputText employeeNameOutputText=new HtmlOutputText();
	private List<SelectItem> employeeOptions = new ArrayList<SelectItem>();
	private Employee curentEmp;
	
	// Actions ------------------------------------------------------------------------------------
	
	public ProjectActivityMB() {
		this.idActivity=-1;


	}

	public void init() {
		
		Map<String, Object> userSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		// If the user is logged in
		if( userSession.get("username")!=null ) {
			curentEmp = fes.findItem((String) userSession.get("username"));
		}
		// Isn't logged in, redirect to login page
		else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
						"/login.xhtml"
					);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//on recupere l' id de l'activit� pass� par l url 
		String 	idActivityString = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idActivity");
	
		//on recupere l'id de l'employe connect�
		idEmployee=1;
		
		//on v�rifie l id r�cup�r�
		if(idActivityString!= null && idActivityString!="") {
			this.idActivity=Integer.parseInt(idActivityString);
			this.activity=fas.findItem(idActivity);
			this.project=this.activity.getProject();
		} else if(this.idActivity == -1) {
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + 
						"/activity-notfound.xhtml"
					);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		this.updateAffichage();
				
	}
	
	public int getNewIdEmployee(){
		return this.newIdEmployee;
	}
	public void setNewIdEmployee(int id){
		this.newIdEmployee=id;
	}
	
	public void modifierEmployee(){
		this.modif=true;
		this.updateAffichage();
		
	}
	
	//fonction qui va mettre a jour l'affichage en fonction 
	public void updateAffichage()
	{
		if(!this.modif)
		{
			this.modifMode="false";
			this.readMode="true";
			//on verifie si l'utilisateur est chef de projet
			if(this.estChefDeProjet())
			{
				this.afficherBoutonModifier="true";
				this.afficherBoutonEnleverHeures="true";
			}
			else 
			{
				this.afficherBoutonModifier="false";
				this.afficherBoutonEnleverHeures="false";
				
			}
			
		} else {
			
			this.afficherBoutonModifier="false";
			this.modifMode="true";
			this.readMode="false";
			//on verifie si l'utilisateur est chef de projet
			if(this.estChefDeProjet())
			{
				this.afficherBoutonEnleverHeures="true";
			}
			else 
			{
				this.afficherBoutonEnleverHeures="false";
			}
			
		}
		
		if(this.checkUserActivity() || this.estChefDeProjet())
			this.afficherFormulaireAjout="true";
		else 
			this.afficherFormulaireAjout="false";
		
		if (this.activity.getEstTermine()==0)
		{
			this.statut="EN COURS";
			this.afficherBoutonTerminer="true";
			if(this.checkUserActivity() || this.estChefDeProjet())
				this.afficherBoutonTerminer="true";
			else 
				this.afficherBoutonTerminer="false";
			
		} else {
			this.statut="Terminée";
			this.afficherBoutonTerminer="false";
		}
	}
	public void validerModificationEmployee(){
		fas.modifierEmployee(this.idActivity,this.newIdEmployee);
		activity.setDescription(this.activityDescription);
		fas.updateItem(activity);
		
		//on repasse en mode lecture
		this.modif=false;
		this.updateAffichage();
	}
	public void annulerModificationEmployee(){
		this.modif=false;
		this.updateAffichage();
	}
	public void terminerActivity(){
		activity.setEstTermine(1);
		this.updateAffichage();
	}
	public void ajouterCharge(){
		if(this.chargeAAjouter>0)
			fas.ajouterCharge(this.idActivity, this.chargeAAjouter,this.curentEmp.getIdemployee());
	}
	public void enleverCharge(){
		if(this.chargeAAjouter>0)
		{
			fas.ajouterCharge(this.idActivity, ((-1)*this.chargeAAjouter),this.curentEmp.getIdemployee());
		}
	}
	public int getActivityCharge(){
		return this.activity.getCharge();
	}
	public String getProjectName(){
		return this.project.getName();
	}
	public int getProjectId() {
		return this.project.getIdproject();
	}
	public String getEmployeeName(){
		Employee e=this.project.getEmployee();
		return e.getFirstname()+" "+e.getLastname();
	}
	public int getEmployeeId(){
		Employee e=this.project.getEmployee();
		return e.getIdemployee();
	}
	public int getActivityNombreHeure(){
		return fas.getNombreHeures(this.idActivity);
	}


	
	public String getActivityDescription(){
		return fas.findItem(this.idActivity).getDescription();
	}
	public void setActivityDescription(String description){
		this.activityDescription=description;
	}
	public int getChargeAAjouter(){
		return this.chargeAAjouter;
	}
	
	public void setChargeAAjouter(int chargeAAjouter){
		 this.chargeAAjouter= chargeAAjouter;
	}	
	
	public void setEmployeeNameOutputText(HtmlOutputText employeeNameOutputText){
		 this.employeeNameOutputText= employeeNameOutputText;
	}	
	public HtmlOutputText getEmployeeNameOutputText() {
		return this.employeeNameOutputText;
	}
	public String getModifMode() {
		return this.modifMode;
	}
	public String getReadMode() {
		return this.readMode;
	}
	public String getAfficherBoutonModifier() {
		return this.afficherBoutonModifier;
	}
	public String getAfficherBoutonEnleverHeures() {
		return this.afficherBoutonEnleverHeures;
	}
	public String getAfficherBoutonTerminer() {
		return this.afficherBoutonTerminer;
	}
	public String getAfficherFormulaireAjout() {
		return this.afficherFormulaireAjout;
	}
	public String getStatut() {
		return this.statut;
	}

	
	
	
	/*Recuperation de listes*/
	
	//construit une list de "select items" a partir de la liste des employ�s
    public List<SelectItem> getEmployeeOptions() {
    	List<Employee> liste = new ArrayList<Employee>();
    	for(Employee e : fes.findAllEmployee()){
    		employeeOptions.add(new SelectItem(e.getIdemployee(), e.getFirstname()+" "+e.getLastname()));
    	}
        return employeeOptions;
    }
    
	public Collection<Avancement> getListAvancement() {
		return fas.getAllAvancementByActivity(this.idActivity);
	}
	
	/*check si l'employ� connecter est le chef de projet*/
	public Boolean estChefDeProjet()
	{
		//return true;
		return fps.checkChefDeProjet(this.activity.getProject(), this.curentEmp);
	}
	public Boolean checkUserActivity() {
		if(this.curentEmp.getIdemployee()==this.activity.getEmployee().getIdemployee())
			return true;
		else 
			return false;
	}
	
	/* Fonction pour recuper string pour le tableau*/
	public String getNameFromEmployee(Integer id) {
		return fes.getNameFromEmployee(id) ;
	}
	
	public String getNombreHeuresString(int nombre)
	{
		if(nombre>0)
		{
			return "+"+nombre;
		} else {
			return ""+nombre;
		}
	}
	
	public String getDateString(Date date)
	{
		//	* Definition du format utilise pour les dates
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		return dateFormat.format(date);
	}
	
}
