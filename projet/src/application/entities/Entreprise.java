package application.entities;

import java.util.HashMap;
import java.util.Map;

public class Entreprise {
	private int id;
	private String nom_Entreprise;
	private HashMap<Integer,Salarié> liste_salariés;
	public Entreprise(String nom_Entreprise, HashMap<Integer, Salarié> liste_salariés) {
		super();
		this.nom_Entreprise = nom_Entreprise;
		liste_salariés=new HashMap();
	}
	
	public Entreprise(int id, String nom_Entreprise) {
		super();
		this.id = id;
		this.nom_Entreprise = nom_Entreprise;
		
	}

	public Entreprise(String nom_Entreprise) {
		// TODO Auto-generated constructor stub
		this.nom_Entreprise=nom_Entreprise;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_Entreprise() {
		return nom_Entreprise;
	}

	public void setNom_Entreprise(String nom_Entreprise) {
		this.nom_Entreprise = nom_Entreprise;
	}

	public HashMap<Integer, Salarié> getListe_salariés() {
		return liste_salariés;
	}

	public void setListe_salariés(HashMap<Integer, Salarié> liste_salariés) {
		this.liste_salariés = liste_salariés;
	}

	
	
	

}
