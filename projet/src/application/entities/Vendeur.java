package application.entities;

public class Vendeur extends Salarié {
 private double vente;
 private double pourcentage;
	public Vendeur(int num_matricule, String nom, String email, String role,double annee_rec, double salaire_fixe,double vente,double pourcentage) {
		super(num_matricule, nom, email, role, annee_rec, salaire_fixe);
		this.pourcentage=pourcentage;
		this.vente=vente;
	}
	
	public double getVente() {
		return vente;
	}
		public void setVente(double vente) {
		this.vente = vente;
	}
	public double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	@Override
	public String toString() {
		return "Vendeur [vente=" + vente + ", pourcentage=" + pourcentage + ", getVente()=" + getVente()
				+ ", getPourcentage()=" + getPourcentage() + ", getMatricule()=" + getMatricule() + ", getNom()="
				+ getNom() + ", getEmail()=" + getEmail() + ", getRole()=" + getRole() + ", getAnneRecruit()="
				+ getAnneRecruit() + ", getSalaire()=" + getSalaire() + "]";
	}
	

}
