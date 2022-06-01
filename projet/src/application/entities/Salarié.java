package application.entities;

public class Salarié {
	private int Matricule;
	private String Nom;
	private String Email;
	private String role;
	private double AnneRecruit;
	private double Salaire;
	public Salarié() {
		
	}
	public int getMatricule() {
		return Matricule;
	}
	public void setMatricule(int matricule) {
		Matricule = matricule;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public double getAnneRecruit() {
		return AnneRecruit;
	}
	public void setAnneRecruit(double anneRecruit) {
		AnneRecruit = anneRecruit;
	}
	public double getSalaire() {
		return Salaire;
	}
	public void setSalaire(double salaire) {
		Salaire = salaire;
	}
	public Salarié(int matricule, String nom, String email, String role, double anneRecruit, double salaire) {
		super();
		Matricule = matricule;
		Nom = nom;
		Email = email;
		this.role = role;
		AnneRecruit = anneRecruit;
		Salaire = salaire;
	}
}