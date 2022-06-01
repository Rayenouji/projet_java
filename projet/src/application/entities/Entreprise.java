package application.entities;

import java.util.HashMap;
import java.util.Map;

public class Entreprise {
	private int id;
	private String nom_Entreprise;
	private HashMap<Integer,Salari�> liste_salari�s;
	public Entreprise(String nom_Entreprise, HashMap<Integer, Salari�> liste_salari�s) {
		super();
		this.nom_Entreprise = nom_Entreprise;
		liste_salari�s=new HashMap();
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

	public HashMap<Integer, Salari�> getListe_salari�s() {
		return liste_salari�s;
	}

	public void setListe_salari�s(HashMap<Integer, Salari�> liste_salari�s) {
		this.liste_salari�s = liste_salari�s;
	}

	
	
	

}
