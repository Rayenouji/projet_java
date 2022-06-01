package application.entities;

public class Employes extends Salarié {
	private double Phsupp;
	private double Hsupp;
	
	public Employes(int num_matricule, String nom, String email, String role, double annee_rec, double salaire_fixe,
			double hSupp, double pHsupp) {
		super(num_matricule, nom, email, role, annee_rec, salaire_fixe);
		Hsupp = hSupp;
		Phsupp = pHsupp;
	}

	@Override
	public String toString() {
		return "Employes [Phsupp=" + Phsupp + ", Hsupp=" + Hsupp + ", getMatricule()=" + getMatricule() + ", getNom()="
				+ getNom() + ", getEmail()=" + getEmail() + ", getRole()=" + getRole() + ", getAnneRecruit()="
				+ getAnneRecruit() + ", getSalaire()=" + getSalaire() + 
		 "]";
	}

	public double getPhsupp() {
		return Phsupp;
	}
	public void setPhsupp(double phsupp) {
		Phsupp = phsupp;
	}
	public double getHsupp() {
		return Hsupp;
	}
	public void setHsupp(double hsupp) {
		Hsupp = hsupp;
	}

	

}
